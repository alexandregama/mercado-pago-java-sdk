package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.oauth.TokenGeneratableApi;
import com.mercadopago.api.service.SellerConnectableAccount;

public interface AuthorizationApi {

	SellerConnectableAccount sellerConnect();
	
	TokenGeneratableApi tokenApi();
	
	AccountConnectOAuthApi accountConnectApiUsing(MercadoPagoToken token);
	
}
