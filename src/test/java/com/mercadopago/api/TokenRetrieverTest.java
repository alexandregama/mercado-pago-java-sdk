package com.mercadopago.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TokenRetrieverTest {

	@Test
	public void shouldGetANewTokenWhenCredentialsAreCorrect() throws Exception {
		TokenClientCredentials credentials = new TokenClientCredentialsReader().getCredentials();
		
		MercadoPagoClient mercadoPago = new MercadoPagoJerseyClient();
		MercadoPagoToken token = mercadoPago.retrieveNewTokenUsing(credentials);
		
		assertNotNull(token.getAccessToken());
	}
	
}
