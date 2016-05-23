package com.mercadopago.api;

public interface MercadoPagoClient {

	PaymentMethodClient paymentMethods();
	
	PreferenceClientApi preferences();

}