package com.mercadopago.api.internal;

import com.mercadopago.api.service.PaymentApi;
import com.mercadopago.api.service.PaymentMethodClientApi;
import com.mercadopago.api.service.PreferenceClientApi;

public interface MercadoPagoClientApi {

	PaymentMethodClientApi paymentMethods();
	
	PreferenceClientApi preferences();
	
	PaymentApi payments();

}