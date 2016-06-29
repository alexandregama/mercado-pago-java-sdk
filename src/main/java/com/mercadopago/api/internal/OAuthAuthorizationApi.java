package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.TokenGeneratableApi;
import com.mercadopago.api.oauth.MercadoPagoProductionTokenApi;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.api.token.MercadoPagoCredentials;

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
	public TokenGeneratableApi tokenApi() {
		return new MercadoPagoProductionTokenApi(credentials);
	}

	@Override
	public AccountConnectOAuthApi accountConnectApiUsing(MercadoPagoToken token) {
		return new AccountConnectOAuthApi(token);
	}

}
