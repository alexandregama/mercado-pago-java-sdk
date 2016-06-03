package com.mercadopago.api;

public class TokenClientCredentials {

	private final String clientId;
	private final String clientSecret;

	public TokenClientCredentials(final String clientId, final String clientSecret) {
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
