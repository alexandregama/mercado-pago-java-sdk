package com.mercadopago.token;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TokenRetrieverTest {

	@Test
	public void shouldGetANewTokenWhenCredentialsAreCorrect() throws Exception {
		MercadoPagoCredentials credentials = new TokenClientCredentialsReader().getCredentialsForFile("config.properties");

		MercadoPagoProductionToken token = MercadoPagoTokenGenerator.generateProductionCodeUsing(credentials);
		
		assertNotNull(token.getAccessToken());
	}
	
}
