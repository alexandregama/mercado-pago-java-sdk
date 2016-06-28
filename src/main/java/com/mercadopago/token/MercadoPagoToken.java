package com.mercadopago.token;

public interface MercadoPagoToken {

	public static final String CLIENT_ID = "client_id";
	
	public static final String SECRET_KEY = "secret_key";
	
	String getAccessToken();
	
}
