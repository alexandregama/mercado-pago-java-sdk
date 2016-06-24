package com.mercadopago.api.internal;

import com.mercadopago.api.service.JerseyPaymentApi;
import com.mercadopago.api.service.JerseyPaymentMethodApi;
import com.mercadopago.api.service.JerseyPreferenceApi;

public interface MercadoPagoApi {

	JerseyPaymentMethodApi paymentMethods();
	
	JerseyPreferenceApi preferences();
	
	JerseyPaymentApi payments();

}