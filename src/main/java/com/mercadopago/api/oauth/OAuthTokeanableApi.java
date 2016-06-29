package com.mercadopago.api.oauth;

public interface OAuthTokeanableApi {

	MercadoPagoProductionToken generateProductionToken();

	MercadoPagoSandboxToken generateSandboxTokenUsing(String accessTokenUsedOnSandbox);
	
}
