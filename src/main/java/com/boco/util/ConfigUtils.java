package com.boco.util;

import com.boco.config.ExtPropertyPlaceholderConfigurer;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 *
 */
public class ConfigUtils {
	private static ExtPropertyPlaceholderConfigurer configurer =
            (ExtPropertyPlaceholderConfigurer)SpringContextUtil.getBean("propertyConfigurer");

	/**
	 * 
	 * 
	 * @param key
	 * @return String
	 * @since 1.0.0
	 */
	public static String getValueString(String key) {
		String value = configurer.getConfigVal(key);
		return value;
	}

	/**
	 * 
	 * 
	 * @param key
	 * @param defaultValue
	 * @return String
	 * @since 1.0.0
	 */
	public static String getValueString(String key, String defaultValue) {
		String value = getValueString(key);
		if (StringUtils.isBlank(value)) {
			value = defaultValue;
		}
		return value;
	}

	public static Properties getProperties(){
		return configurer.getProperties();
	}
}
