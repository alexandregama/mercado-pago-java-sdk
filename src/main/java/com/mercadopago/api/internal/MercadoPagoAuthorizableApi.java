package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoGeneratableTokenApi;
import com.mercadopago.api.service.MercadoPagoSellerConnectableUrlAccount;

public interface MercadoPagoAuthorizableApi {

	MercadoPagoSellerConnectableUrlAccount askForSellerAccountConnection();
	
	MercadoPagoGeneratableTokenApi tokenApi();
	
	MercadoPagoAccountConnectOAuthApi accountConnectApiUsing(String fixedAccessToken);
	
}
