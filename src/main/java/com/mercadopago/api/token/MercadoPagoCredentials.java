package com.mercadopago.api.token;

public class MercadoPagoCredentials {

	private final String clientId;
	private final String secretKey;

	public MercadoPagoCredentials(final String clientId, final String secretKey) {
		this.clientId = clientId;
		this.secretKey = secretKey;
	}
	
	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return secretKey;
	}
	
}
