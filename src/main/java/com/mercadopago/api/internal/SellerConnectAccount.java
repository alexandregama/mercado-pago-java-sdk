package com.mercadopago.api.internal;

import com.mercadopago.api.service.SellerConnectableAccount;

/**
 * 
 * @author Alexandre Gama
 *
 * Classe responsible for create a url to be used when seller is connectings its own account into your application
 * You can use it when you would like to access seller's account
 * 
 */
public class SellerConnectAccount implements SellerConnectableAccount {

	private String clientId;

	/**
	 * 
	 * @param clientId
	 * You must pass your clientId to this method. Notice that you can retrieve this one by creating your own application on {@link https://applications.mercadopago.com/}
	 */
	public SellerConnectAccount(String clientId) {
		this.clientId = clientId;
	}

	public String redirectingTo(String url) {
		return String.format("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=%s&response_type=code&platform_id=mp&redirect_uri=%s", clientId, url);
	}

}
