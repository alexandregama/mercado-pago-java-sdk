package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoOAuthTokeanableApi;
import com.mercadopago.api.oauth.MercadoPagoOAuthTokenApi;
import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.token.MercadoPagoCredentials;

public class MercadoPagoOAuthApiImpl implements MercadoPagoOAuthApi {

	private MercadoPagoCredentials credentials;

	public MercadoPagoOAuthApiImpl(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}
	
	@Override
	public SellerConnectableAccount sellerConnect() {
		return new SellerConnectAccount(credentials);
	}

	@Override
	public MercadoPagoOAuthTokeanableApi tokenApi() {
		return new MercadoPagoOAuthTokenApi(credentials);
	}

	@Override
	public AccountConnectOAuthApi accountConnectApiUsing(String accessToken) {
		return new AccountConnectOAuthApi(accessToken);
	}

}
