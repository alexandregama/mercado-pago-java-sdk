package com.mercadopago.api;

public class MercadoPagoJerseyClient implements MercadoPagoClientApi {

	private final MercadoPagoToken token;
	
	public MercadoPagoJerseyClient(final MercadoPagoToken token) {
		this.token = token;
	}

	public PaymentMethodClientApi paymentMethods() {
		return new PaymentMethodClientApi(token);
	}

	public PreferenceClientApi preferences() {
		return new PreferenceClientApi(token);
	}

}
