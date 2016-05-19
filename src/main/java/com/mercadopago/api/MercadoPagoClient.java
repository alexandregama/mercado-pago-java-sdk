package com.mercadopago.api;

import java.util.List;

import com.mercadopago.payment.PaymentMethod;
import com.mercadopago.preference.Preference;

public interface MercadoPagoClient {

	MercadoPagoToken retrieveNewTokenUsing(TokenClientCredentials clientCredentials);

	List<PaymentMethod> retrieveAllPaymentMethodsUsing(MercadoPagoToken token);
	
	void createPreference(Preference preference, MercadoPagoToken token);

}