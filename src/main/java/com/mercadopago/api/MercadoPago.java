package com.mercadopago.api;

public class MercadoPago {
	
	private MercadoPagoClient client;
	
	public MercadoPago() {
		this.client = new MercadoPagoJerseyClient(); 
	}

	public MercadoPagoToken getToken(TokenClientCredentials clientCredentials) {
		MercadoPagoToken token = client.retrieveNewTokenUsing(clientCredentials);
		
		return token;
	}

}
