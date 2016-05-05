package com.mercadopago;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TokenRetriever {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		TokenClientCredentials clientCredentials = new TokenClientCredentialsReader().getCredentials();
		
		MercadoPagoToken token = MercadoPago.getToken(clientCredentials);
		
		System.out.println(token.getAccessToken());
	}
	
}
