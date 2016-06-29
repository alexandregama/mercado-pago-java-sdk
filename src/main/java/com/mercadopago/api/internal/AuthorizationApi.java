package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.OAuthTokeanableApi;
import com.mercadopago.api.service.SellerConnectableAccount;

public interface AuthorizationApi {

	SellerConnectableAccount sellerConnect();
	
	OAuthTokeanableApi tokenApi();
	
	AccountConnectOAuthApi accountConnectApiUsing(String accessToken);
	
}
