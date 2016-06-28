package com.mercadopago.token;

public class MercadoPagoSandboxToken implements MercadoPagoToken {

	private String accessToken;

	public MercadoPagoSandboxToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	@Override
	public String getAccessToken() {
		return accessToken;
	}

}
