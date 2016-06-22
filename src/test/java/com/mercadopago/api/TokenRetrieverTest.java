package com.mercadopago.api;

import static com.mercadopago.token.MercadoPagoTokenGenerator.ENVIRONMENT_MODE.PRODUCTION;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mercadopago.token.MercadoPagoTokenGenerator;

public class TokenRetrieverTest {

	@Test
	public void shouldGetANewTokenWhenCredentialsAreCorrect() throws Exception {
		TokenCredentials credentials = new TokenClientCredentialsReader().getCredentialsForFile("config.properties");

		MercadoPagoTokenGenerator tokenGenerator = new MercadoPagoTokenGenerator();
		MercadoPagoToken token = tokenGenerator.generateUsing(credentials, PRODUCTION);
		
		assertNotNull(token.getAccessToken());
	}
	
}
