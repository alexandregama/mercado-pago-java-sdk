package com.mercadopago.token;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.internal.MercadoPagoApiFactory;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.token.MercadoPagoCredentials;
import com.mercadopago.api.token.PropertiesReader;

public class TokenRetrieverTest {

	private static MercadoPagoCredentials credentials;

	@BeforeClass
	public static void setup() {
		PropertiesReader propertiesReader = new PropertiesReader();
		String clientId = propertiesReader.getPropertyValueFrom(MercadoPagoToken.CLIENT_ID);
		String secretKey = propertiesReader.getPropertyValueFrom(MercadoPagoToken.SECRET_KEY);
		credentials = new MercadoPagoCredentials(clientId, secretKey);
	}
	
	@Test
	public void shouldGetANewTokenWhenCredentialsAreCorrect() throws Exception {
		MercadoPagoToken token = MercadoPagoApiFactory.generateProductionTokenUsing(credentials);
		
		System.out.println(token.getAccessToken());
		assertNotNull(token.getAccessToken());
	}
	
}
