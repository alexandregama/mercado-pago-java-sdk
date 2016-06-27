package com.mercadopago.api.internal;

import com.google.common.base.MoreObjects;
import com.mercadopago.api.service.PaymentApi;
import com.mercadopago.api.service.PaymentMethodApi;
import com.mercadopago.api.service.PreferenceApi;
import com.mercadopago.token.MercadoPagoToken;

public class MercadoPagoJerseyApi implements MercadoPagoApi {

	private final MercadoPagoToken token;
	
	public MercadoPagoJerseyApi(final MercadoPagoToken token) {
		this.token = token;
	}

	public PaymentMethodApi paymentMethods() {
		return new JerseyPaymentMethodApi(token);
	}

	public PreferenceApi preferences() {
		return new JerseyPreferenceApi(token);
	}
	
	@Override
	public PaymentApi payments() {
		return new JerseyPaymentApi(token);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("token", token)
		.toString();
	}
	
}