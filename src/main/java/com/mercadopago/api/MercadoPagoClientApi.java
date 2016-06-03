package com.mercadopago.api;

public interface MercadoPagoClientApi {

	PaymentMethodClientApi paymentMethods();
	
	PreferenceClientApi preferences();

}