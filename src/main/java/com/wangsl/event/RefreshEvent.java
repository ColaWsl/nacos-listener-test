package com.wangsl.event;

import org.springframework.context.ApplicationEvent;

public class RefreshEvent extends ApplicationEvent {

	private final String content;

	public RefreshEvent(Object source, String content) {
		super(source);
		System.out.println("RefreshEvent 构造");
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
