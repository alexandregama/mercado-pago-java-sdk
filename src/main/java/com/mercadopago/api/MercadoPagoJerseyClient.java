package com.mercadopago.api;

public class MercadoPagoJerseyClient implements MercadoPagoClient {

	private final MercadoPagoToken token;
	
	public MercadoPagoJerseyClient(final MercadoPagoToken token) {
		this.token = token;
	}

	public PaymentMethodClient paymentMethods() {
		return new PaymentMethodClient(token);
	}

	public PreferenceClient preferences() {
		return new PreferenceClient(token);
	}

}
