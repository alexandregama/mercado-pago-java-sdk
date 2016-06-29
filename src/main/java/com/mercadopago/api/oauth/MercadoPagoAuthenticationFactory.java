package com.mercadopago.api.oauth;

import com.mercadopago.token.MercadoPagoCredentials;

public class MercadoPagoAuthenticationFactory {

	public static MercadoPagoToken generateSandboxTokenUsing(String sandboxAccessToken) {
		return new MercadoPagoSandboxTokenApi(sandboxAccessToken).generateToken();
	}

	public static MercadoPagoToken generateProductionTokenUsing(MercadoPagoCredentials credentials) {
		return new MercadoPagoProductionTokenApi(credentials).generateToken();
	}

}
