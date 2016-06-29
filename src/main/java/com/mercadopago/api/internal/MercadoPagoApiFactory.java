package com.mercadopago.api.internal;

import com.mercadopago.api.internal.MercadoPagoApi;
import com.mercadopago.api.internal.MercadoPagoJerseyApi;
import com.mercadopago.api.oauth.MercadoPagoToken;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class MercadoPagoApiFactory {

	public static MercadoPagoApi from(MercadoPagoToken token) {
		return new MercadoPagoJerseyApi(token);
	}
	 
}
