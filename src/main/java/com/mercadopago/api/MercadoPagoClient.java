package com.mercadopago.api;

import java.util.List;

import com.mercadopago.payment.PaymentMethod;

public interface MercadoPagoClient {

	MercadoPagoToken retrieveNewTokenUsing(TokenClientCredentials clientCredentials);

	List<PaymentMethod> retrieveAllPaymentMethodsUsing(MercadoPagoToken token);

}