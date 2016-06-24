package com.mercadopago.api;

import com.mercadopago.payment.PaymentApi;

public interface MercadoPagoClientApi {

	PaymentMethodClientApi paymentMethods();
	
	PreferenceClientApi preferences();
	
	PaymentApi payments();

}