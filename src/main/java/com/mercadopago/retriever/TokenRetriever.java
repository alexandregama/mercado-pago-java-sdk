package com.mercadopago.retriever;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.mercadopago.api.MercadoPago;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;

public class TokenRetriever {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		TokenClientCredentials clientCredentials = new TokenClientCredentialsReader().getCredentials();
		
		MercadoPagoToken token = new MercadoPago().getToken(clientCredentials);
		
		System.out.println(token);
	}
	
}
