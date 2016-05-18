package com.mercadopago.api;

import java.util.List;

import com.mercadopago.payment.PaymentMethod;

public class MercadoPago {
	
	private MercadoPagoClient client;
	
	public MercadoPago() {
		this.client = new MercadoPagoJerseyClient(); 
	}

	public MercadoPagoToken getToken(TokenClientCredentials clientCredentials) {
		MercadoPagoToken token = client.retrieveNewTokenUsing(clientCredentials);
		
		return token;
	}

	public List<PaymentMethod> getPaymentMethods(MercadoPagoToken token) {
		return client.retrieveAllPaymentMethodsUsing(token);
	}

}
