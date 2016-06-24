package com.mercadopago.api.service;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.api.paymentmethod.PaymentMethodApi;
import com.mercadopago.paymentmethod.PaymentMethod;
import com.mercadopago.token.MercadoPagoToken;

public class JerseyPaymentMethodApi implements PaymentMethodApi {

	private static final String MERCADO_PAGO_API = "https://api.mercadopago.com/v1";
	
	private final MercadoPagoToken token;

	public JerseyPaymentMethodApi(final MercadoPagoToken token) {
		this.token = token;
	}
	
	public List<PaymentMethod> getAll() {
		Client client = ClientBuilder.newClient();
		
		List<PaymentMethod> methods = paymentMethods(client);
		
		return methods;
	}

	public Optional<PaymentMethod> findBy(String paymentMethodId) {
		Client client = ClientBuilder.newClient();
		
		List<PaymentMethod> paymentMethods = paymentMethods(client);
		
		Optional<PaymentMethod> paymentMethodFound = paymentMethods
			.stream()
			.filter(p -> p.getId().equals(paymentMethodId))
			.findFirst();
		
		return paymentMethodFound;
	}

	private List<PaymentMethod> paymentMethods(Client client) {
		Response response = client
				.target(MERCADO_PAGO_API)
				.path("payment_methods")
				.queryParam("access_token", token.getAccessToken())
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON) 
				.get();
		List<PaymentMethod> methods = response.readEntity(new GenericType<List<PaymentMethod>>() {});
		
		return methods;
	}
	
}
