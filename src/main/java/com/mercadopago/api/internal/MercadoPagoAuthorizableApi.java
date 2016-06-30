package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.oauth.MercadoPagoTokenGeneratableApi;
import com.mercadopago.api.service.MercadoPagoSellerConnectableAccount;

public interface MercadoPagoAuthorizableApi {

	MercadoPagoSellerConnectableAccount sellerConnect();
	
	MercadoPagoTokenGeneratableApi tokenApi();
	
	MercadoPagoAccountConnectOAuthApi accountConnectApiUsing(MercadoPagoToken token);
	
}
