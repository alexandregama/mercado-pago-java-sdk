package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.OAuthTokeanableApi;
import com.mercadopago.api.oauth.MercadoPagoOAuthTokenApi;
import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.token.MercadoPagoCredentials;

public class OAuthAuthorizationApi implements AuthorizationApi {

	private MercadoPagoCredentials credentials;

	public OAuthAuthorizationApi(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}
	
	@Override
	public SellerConnectableAccount sellerConnect() {
		return new SellerConnectAccount(credentials);
	}

	@Override
	public OAuthTokeanableApi tokenApi() {
		return new MercadoPagoOAuthTokenApi(credentials);
	}

	@Override
	public AccountConnectOAuthApi accountConnectApiUsing(String accessToken) {
		return new AccountConnectOAuthApi(accessToken);
	}

}
