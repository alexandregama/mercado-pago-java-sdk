package com.mercadopago.api.oauth;

public interface OAuthTokeanableApi {

	MercadoPagoProductionToken generateProductionTokenUsing();

	MercadoPagoProductionToken generateSandboxTokenUsing(String accessTokenUsedOnSandbox);
	
}
