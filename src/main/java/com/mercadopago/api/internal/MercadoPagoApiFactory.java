package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoProductionTokenApi;
import com.mercadopago.api.oauth.MercadoPagoSandboxTokenApi;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.token.MercadoPagoCredentials;

/**
 * 
 * @author Alexandre Gama
 * 
 * Class to be used when you must use Mercado Pago operations
 * 
 */
public class MercadoPagoApiFactory {

	public static MercadoPagoApi from(MercadoPagoToken token) {
		return new MercadoPagoJerseyApi(token);
	}
	
	public static MercadoPagoToken generateProductionTokenUsing(MercadoPagoCredentials credentials) {
		return new MercadoPagoProductionTokenApi(credentials).generateToken();
	}
	
	public static MercadoPagoToken generateSandboxTokenUsing(String sandboxAccessToken) {
		return new MercadoPagoSandboxTokenApi(sandboxAccessToken).generateToken();
	}
	 
}
