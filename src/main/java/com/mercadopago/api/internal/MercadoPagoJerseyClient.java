package com.mercadopago.api.internal;

import com.google.common.base.MoreObjects;
import com.mercadopago.api.service.JerseyPaymentApi;
import com.mercadopago.api.service.JerseyPaymentMethodApi;
import com.mercadopago.api.service.JerseyPreferenceApi;
import com.mercadopago.token.MercadoPagoToken;

public class MercadoPagoJerseyClient implements MercadoPagoApi {

	private final MercadoPagoToken token;
	
	public MercadoPagoJerseyClient(final MercadoPagoToken token) {
		this.token = token;
	}

	public JerseyPaymentMethodApi paymentMethods() {
		return new JerseyPaymentMethodApi(token);
	}

	public JerseyPreferenceApi preferences() {
		return new JerseyPreferenceApi(token);
	}
	
	@Override
	public JerseyPaymentApi payments() {
		return new JerseyPaymentApi(token);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("token", token)
		.toString();
	}

	
}
