package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoGeneratableTokenApi;
import com.mercadopago.api.oauth.MercadoPagoProductionTokenApi;
import com.mercadopago.api.service.MercadoPagoSellerConnectableUrlAccount;
import com.mercadopago.api.token.MercadoPagoCredentials;

public class MercadoPagoOAuthAuthorizationApi implements MercadoPagoAuthorizableApi {

	private MercadoPagoCredentials credentials;

	public MercadoPagoOAuthAuthorizationApi(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}
	
	@Override
	public MercadoPagoSellerConnectableUrlAccount askForSellerAccountConnection() {
		return new SellerConnectAccount(credentials);
	}

	@Override
	public MercadoPagoGeneratableTokenApi tokenApi() {
		return new MercadoPagoProductionTokenApi(credentials);
	}

	@Override
	public MercadoPagoAccountConnectOAuthApi accountConnectApiUsing(String fixedAccessToken) {
		return new MercadoPagoAccountConnectOAuthApi(fixedAccessToken);
	}

}
