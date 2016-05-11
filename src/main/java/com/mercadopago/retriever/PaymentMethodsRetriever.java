package com.mercadopago.retriever;

import java.util.List;

import com.mercadopago.api.MercadoPago;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;
import com.mercadopago.payment.PaymentMethod;

public class PaymentMethodsRetriever {

	public static void main(String[] args) {
		TokenClientCredentials clientCredentials = new TokenClientCredentialsReader().getCredentials();
		
		MercadoPago mercadoPago = new MercadoPago();
		MercadoPagoToken token = mercadoPago.getToken(clientCredentials);
		List<PaymentMethod> methods = mercadoPago.getPaymentMethods(token);
		
		System.out.println(methods);
	}
	
}
