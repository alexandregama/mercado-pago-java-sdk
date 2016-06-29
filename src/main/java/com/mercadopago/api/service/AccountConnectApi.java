package com.mercadopago.api.service;

import com.mercadopago.api.oauth.MercadoPagoToken;

public interface AccountConnectApi {
	
	MercadoPagoToken getSellerCredentials();
	
}
