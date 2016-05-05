package com.mercadopago;

public class TokenClientCredentials {

	private String clientId;
	private String clientSecret;

	public TokenClientCredentials(String clientId, String clientSecret) {
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
