package com.mercadopago.api.internal;

import com.mercadopago.api.service.JerseyPaymentApi;
import com.mercadopago.api.service.PaymentMethodApi;
import com.mercadopago.api.service.JerseyPreferenceApi;

public interface MercadoPagoApi {

	PaymentMethodApi paymentMethods();
	
	JerseyPreferenceApi preferences();
	
	JerseyPaymentApi payments();

}