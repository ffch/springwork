package com.cff.springwork.wallet.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeansManager implements ApplicationContextAware {
	/**
	 * 上下文对象实例
	 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据Bean名称获取实例
	 * 
	 * @param name
	 *            Bean注册名称
	 * @return bean实例
	 * @throws BeansException
	 */
	public Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
}
