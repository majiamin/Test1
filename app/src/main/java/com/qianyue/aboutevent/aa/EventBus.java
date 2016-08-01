package com.qianyue.aboutevent.aa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class EventBus {

	private static final String METHOD_NAME = "onEvent";
	private List<Object> subscribers;// 订阅者
	private Class<?> msgClz;// 当前发送的消息的class对象
	// 应该根据消息类型区分订阅者
	private EventBus(){
		subscribers = new ArrayList<Object>();
	}
	private static EventBus instance;
	public static EventBus getDefault(){
		if (instance==null) {
			synchronized (EventBus.class) {
				if (instance == null) {
					instance = new EventBus();
				}
			}
		}
		return instance;
	}
	public synchronized void register(Object object) {
		subscribers.add(object);
	}

	public synchronized void unRegister(Object object) {
		subscribers.remove(object);
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public synchronized void post(Object message) {
		msgClz = message.getClass();
		subscribersDeal(message);
	}

	/**
	 * 让订阅者处理消息
	 * 
	 * @param message
	 */
	private void subscribersDeal(Object message) {
		for (int i = 0; i < subscribers.size(); i++) {// 遍历订阅者
			Class<? extends Object> class1 = subscribers.get(i).getClass();
			try {
				Method eventMethod = class1.getDeclaredMethod(METHOD_NAME, msgClz);
			
				eventMethod.invoke(subscribers.get(i),message);
			} catch (NoSuchMethodException e) {
//				throw new RuntimeException("抱歉,您没有在"
//						+ subscribers.get(i).getClass().getSimpleName()
//						+ "类中定义onEvent("+msgClz.getSimpleName()+")方法");
			} catch (IllegalAccessException e) {
				Log.i("==", "非法权限");
			} catch (IllegalArgumentException e) {
				Log.i("==", "非法参数");
				
			} catch (InvocationTargetException e) {
				
				Log.i("==", "非法目标");
			}

		}
	}
}
