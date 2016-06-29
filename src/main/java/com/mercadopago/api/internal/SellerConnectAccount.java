package com.mercadopago.api.internal;

import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.token.MercadoPagoCredentials;

/**
 * 
 * @author Alexandre Gama
 *
 * Classe responsible for create a url to be used when seller is connectings its own account into your application
 * You can use it when you would like to access seller's account
 * 
 */
public class SellerConnectAccount implements SellerConnectableAccount {

	private MercadoPagoCredentials credentials;

	/**
	 * 
	 * @param clientId
	 * You must pass your clientId to this method. Notice that you can retrieve this one by creating your own application on {@link https://applications.mercadopago.com/}
	 */
	public SellerConnectAccount(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}

	public String redirectingTo(String url) {
		return String.format("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=%s&response_type=code&platform_id=mp&redirect_uri=%s", credentials.getClientId(), url);
	}

}
