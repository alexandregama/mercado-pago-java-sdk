package com.mercadopago.api.factory;

import com.mercadopago.api.internal.MercadoPagoApi;
import com.mercadopago.api.internal.MercadoPagoJerseyApi;
import com.mercadopago.api.oauth.MercadoPagoToken;

public class MercadoPagoFactory {

	public static MercadoPagoApi from(MercadoPagoToken token) {
		return new MercadoPagoJerseyApi(token);
	}
	 
}
