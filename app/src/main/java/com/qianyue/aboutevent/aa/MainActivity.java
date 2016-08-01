package com.qianyue.aboutevent.aa;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyue.qyeventbus.R;


public class MainActivity extends Activity {
	
    private TextView tv;
	private EventBus eventBus;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        initView();
    }

	private void initView() {
		
		tv = (TextView)findViewById(R.id.tv);
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,SecondActivity.class));
			}
		});
	}
	
	public void onEvent(MyEvent myEvent){
		Toast.makeText(this, "收到了消息"+myEvent.getName(), Toast.LENGTH_SHORT).show();
		tv.setText("这是收到的消息"+myEvent.getName());
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		eventBus.unRegister(this);
	}
}
