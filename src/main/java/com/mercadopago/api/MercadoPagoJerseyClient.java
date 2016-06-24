package com.mercadopago.api;

import com.google.common.base.MoreObjects;
import com.mercadopago.payment.PaymentApi;

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
	
	@Override
	public PaymentApi payments() {
		return new PaymentApi(token);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("token", token)
		.toString();
	}

	
}
