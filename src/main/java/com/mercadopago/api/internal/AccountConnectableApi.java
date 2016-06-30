package com.mercadopago.api.internal;

import com.mercadopago.api.internal.MercadoPagoAccountConnectOAuthApi.MercadoPagoUrlRedirection;

public interface AccountConnectableApi {
	
	MercadoPagoUrlRedirection getMercadoPagoTokenFromSellerUsingAuthorizationCode(String authorizationCode);
	
}
