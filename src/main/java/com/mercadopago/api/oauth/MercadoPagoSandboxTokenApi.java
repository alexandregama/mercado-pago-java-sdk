package com.mercadopago.api.oauth;

public class MercadoPagoSandboxTokenApi implements TokenGeneratableApi {

	private String accessToken;

	public MercadoPagoSandboxTokenApi(String sandboxAccessToken) {
		this.accessToken = sandboxAccessToken;
	}

	@Override
	public MercadoPagoToken generateToken() {
		MercadoPagoSandboxToken mercadoPagoToken = new MercadoPagoSandboxToken(accessToken);
		
		return mercadoPagoToken;
	}

}
