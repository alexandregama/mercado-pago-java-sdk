package com.mercadopago.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TokenClientCredentialsReader {

	public TokenClientCredentials getCredentials() {
		Properties properties = new Properties();
		String configFileName = "config.properties";
		InputStream file = getClass().getClassLoader().getResourceAsStream(configFileName);
		if (file != null) {
			try {
				properties.load(file);
				String clientId = properties.getProperty("client_id");
				String clientSecret = properties.getProperty("client_secret");
				return new TokenClientCredentials(clientId, clientSecret);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("You should create a config file into src/main/resources/config.properties");
			} catch (IOException e) {
				throw new RuntimeException("An error occurs while reading config file" , e);
			}
		}
		throw new RuntimeException("An error occurs while reading config file");			
	}
	
}
