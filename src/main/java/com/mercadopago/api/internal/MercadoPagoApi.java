package com.mercadopago.api.internal;

public interface MercadoPagoApi {

	JerseyPaymentMethodApi paymentMethods();
	
	JerseyPreferenceApi preferences();
	
	JerseyPaymentApi payments();

}