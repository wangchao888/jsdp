package com.joinsoft.core.encrypt;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncrypPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static final String key = "joinsoft";

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			String username = props.getProperty("jdbc.username");
			if (username != null) {
				props.setProperty("jdbc.username", EncryptUtils.decrypt(username, key));
			}
			String password = props.getProperty("jdbc.password");
			if (password != null) {
				props.setProperty("jdbc.password", EncryptUtils.decrypt(password, key));
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}
