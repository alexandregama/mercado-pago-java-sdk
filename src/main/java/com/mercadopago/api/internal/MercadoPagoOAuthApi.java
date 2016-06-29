package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoOAuthTokeanableApi;
import com.mercadopago.api.service.SellerConnectableAccount;

public interface MercadoPagoOAuthApi {

	SellerConnectableAccount sellerConnect();
	
	MercadoPagoOAuthTokeanableApi tokenApi();
	
	AccountConnectOAuthApi accountConnectApiUsing(String accessToken);
	
}
