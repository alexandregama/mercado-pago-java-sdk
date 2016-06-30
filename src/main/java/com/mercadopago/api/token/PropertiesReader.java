package com.mercadopago.api.token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public String getPropertyValueFrom(String propertyName) {
		Properties properties = new Properties();
		InputStream file = getClass().getClassLoader().getResourceAsStream("config.properties");
		if (file != null) {
			try {
				properties.load(file);
				return properties.getProperty(propertyName);
			} catch (IOException e) {
				throw new RuntimeException("An error ocurred while trying to retrieve a property from file config.properties");
			}
		}
		return null;
	}
}
