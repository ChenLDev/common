package com.boco.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author chenliang
 */
@Service
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;

	public SpringContextUtil() {

	}
	/**
	 *
	 */
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringContextUtil.ctx = ctx;
	}

	/**
	 * @param beanName
	 * @return
	 * @throws BeansException Object
	 * @since 1.0.0
	 */
	public static Object getBean(String beanName) throws BeansException {
		return ctx.getBean(beanName);
	}

	/**
	 * @param beanName
	 * @return boolean
	 * @since 1.0.0
	 */
	public static boolean containsBean(String beanName) {
		return ctx.containsBean(beanName);
	}

	/**
	 * @return ApplicationContext
	 * @since 1.0.0
	 */
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}


	/**
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		Map map = ctx.getBeansOfType(clazz);
		if (map.isEmpty()) {
			throw new IllegalStateException("未找到bean:" + clazz);
		} else {
			return ctx.getBeansOfType(clazz).values().iterator().next();
		}
	}

	/**
	 *
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> Collection<T> getBeans(Class<T> cls) {
		Map map = ctx.getBeansOfType(cls);
		if (map.isEmpty()) {
			return Collections.EMPTY_LIST;
		} else {
			return map.values();
		}
	}
}
