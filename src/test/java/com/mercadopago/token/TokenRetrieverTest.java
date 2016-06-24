package com.mercadopago.token;

import static com.mercadopago.token.MercadoPagoTokenGenerator.ENVIRONMENT_MODE.PRODUCTION;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mercadopago.token.MercadoPagoToken;
import com.mercadopago.token.MercadoPagoTokenGenerator;
import com.mercadopago.token.TokenClientCredentialsReader;
import com.mercadopago.token.TokenCredentials;

public class TokenRetrieverTest {

	@Test
	public void shouldGetANewTokenWhenCredentialsAreCorrect() throws Exception {
		TokenCredentials credentials = new TokenClientCredentialsReader().getCredentialsForFile("config.properties");

		MercadoPagoTokenGenerator tokenGenerator = new MercadoPagoTokenGenerator();
		MercadoPagoToken token = tokenGenerator.generateUsing(credentials, PRODUCTION);
		
		assertNotNull(token.getAccessToken());
	}
	
}
