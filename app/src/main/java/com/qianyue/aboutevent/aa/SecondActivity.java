package com.qianyue.aboutevent.aa;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.qianyue.qyeventbus.R;


public class SecondActivity extends Activity implements OnClickListener {
	
    private TextView tv1;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getDefault().register(this);
        initView();
    }

	private void initView() {
		tv1 = (TextView)findViewById(R.id.tv1);
		tv1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		MyEvent message = new MyEvent();
		message.setName("xiaoming");
//		SecondEvent message = new SecondEvent();
		EventBus.getDefault().post(message);
	}
	
	
	public void onEvent(MyEvent myEvent){
		tv1.setText("secondactivity也收到了消息"+myEvent.getName());
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unRegister(this);
	}

}
