package com.wangsl.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.wangsl.event.RefreshEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNullApi;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig implements InitializingBean, Listener, ApplicationContextAware {

	private ApplicationContext context;

	private static final String DATA_ID = "nacos-listener-test.yml";
	private static final String GROUP = "test";

	@NacosInjected
	private ConfigService configService;

	@Override
	public void afterPropertiesSet() throws Exception {
		configService.addListener(DATA_ID, GROUP, this);
		System.out.println("AppConfig 属性注入后，执行添加 Nacos Listener");
	}

	@Override
	public Executor getExecutor() {
		return null;
	}

	@Override
	public void receiveConfigInfo(String configInfo) {
		System.out.println("receiveConfigInfo 监听到变更");
		System.out.println("发送 Refresh 事件进行通知");
		context.publishEvent(new RefreshEvent(this, configInfo));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
