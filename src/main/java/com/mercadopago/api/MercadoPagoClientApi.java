package com.mercadopago.api;

import com.mercadopago.payment.PaymentClientApi;

public interface MercadoPagoClientApi {

	PaymentMethodClientApi paymentMethods();
	
	PreferenceClientApi preferences();
	
	PaymentClientApi payments();

}