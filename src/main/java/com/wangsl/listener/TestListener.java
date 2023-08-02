package com.wangsl.listener;

import com.wangsl.event.RefreshEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener {

	@EventListener
	public void listener(RefreshEvent refreshEvent){
		System.out.println("监听到事件");
		String content = refreshEvent.getContent();
		System.out.println("TestListener 接收到事件传递的内容: " + content);
	}
}
