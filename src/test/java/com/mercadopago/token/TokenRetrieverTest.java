package com.mercadopago.token;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

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
		MercadoPagoProductionToken token = MercadoPagoTokenGenerator.generateProductionCodeUsing(credentials);
		
		assertNotNull(token.getAccessToken());
	}
	
}
