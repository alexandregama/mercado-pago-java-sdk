package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoTokenGeneratableApi;
import com.mercadopago.api.oauth.MercadoPagoProductionTokenApi;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.MercadoPagoSellerConnectableAccount;
import com.mercadopago.api.token.MercadoPagoCredentials;

public class MercadoPagoOAuthAuthorizationApi implements MercadoPagoAuthorizableApi {

	private MercadoPagoCredentials credentials;

	public MercadoPagoOAuthAuthorizationApi(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}
	
	@Override
	public MercadoPagoSellerConnectableAccount sellerConnect() {
		return new SellerConnectAccount(credentials);
	}

	@Override
	public MercadoPagoTokenGeneratableApi tokenApi() {
		return new MercadoPagoProductionTokenApi(credentials);
	}

	@Override
	public MercadoPagoAccountConnectOAuthApi accountConnectApiUsing(MercadoPagoToken token) {
		return new MercadoPagoAccountConnectOAuthApi(token);
	}

}
