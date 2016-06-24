package com.mercadopago.api.internal;

import com.mercadopago.api.service.JerseyPaymentApi;
import com.mercadopago.api.service.PaymentMethodApi;
import com.mercadopago.api.service.PreferenceApi;

public interface MercadoPagoApi {

	PaymentMethodApi paymentMethods();
	
	PreferenceApi preferences();
	
	JerseyPaymentApi payments();

}