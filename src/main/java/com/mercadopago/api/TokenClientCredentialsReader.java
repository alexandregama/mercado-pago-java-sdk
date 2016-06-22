package com.mercadopago.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TokenClientCredentialsReader {

	public TokenCredentials getCredentialsForFile(String filenameOnClasspath) {
		Properties properties = new Properties();
		InputStream file = getClass().getClassLoader().getResourceAsStream(filenameOnClasspath);
		if (file != null) {
			try {
				properties.load(file);
				String clientId = properties.getProperty("client_id");
				String clientSecret = properties.getProperty("client_secret");
				
				return new TokenCredentials(clientId, clientSecret);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("You should create a config file into src/main/resources/config.properties");
			} catch (IOException e) {
				throw new RuntimeException("An error occurs while reading config file" , e);
			}
		}
		throw new RuntimeException("An error occurs while reading config file");			
	}
	
}
