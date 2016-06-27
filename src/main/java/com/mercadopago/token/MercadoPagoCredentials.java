package com.mercadopago.token;

public class MercadoPagoCredentials {

	private final String clientId;
	private final String clientSecret;

	public MercadoPagoCredentials(final String clientId, final String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}
	
}
