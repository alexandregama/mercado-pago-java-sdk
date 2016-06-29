package com.mercadopago.api.oauth;

public interface MercadoPagoOAuthTokeanableApi {

	MercadoPagoProductionToken generateProductionTokenUsing();

	MercadoPagoProductionToken generateSandboxTokenUsing(String accessTokenUsedOnSandbox);
	
}
