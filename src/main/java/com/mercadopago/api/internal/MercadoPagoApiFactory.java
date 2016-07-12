package com.mercadopago.api.internal;

import com.mercadopago.api.oauth.MercadoPagoProductionTokenApi;
import com.mercadopago.api.oauth.MercadoPagoSandboxTokenApi;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.token.MercadoPagoCredentials;

/**
 * 
 * @author Alexandre Gama - <a href="https://github.com/alexandregama">GitHub Account</a>
 * 
 * Class to be used when you would like to use Mercado Pago Operations
 * 
 */
public class MercadoPagoApiFactory {

	public static MercadoPagoApi enableApiOperationsFrom(MercadoPagoToken token) {
		return new MercadoPagoJerseyApi(token);
	}
	
	public static MercadoPagoToken generateProductionTokenUsing(MercadoPagoCredentials credentials) {
		return new MercadoPagoProductionTokenApi(credentials).generateToken();
	}
	
	public static MercadoPagoToken generateSandboxTokenUsing(String sandboxAccessToken) {
		return new MercadoPagoSandboxTokenApi(sandboxAccessToken).generateToken();
	}

	public static MercadoPagoAuthorizableApi authorizationFrom(MercadoPagoCredentials credentials) {
		return new MercadoPagoOAuthAuthorizationApi(credentials);
	}
	 
}
