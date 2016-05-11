package com.mercadopago.api;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.payment.PaymentMethod;

public class MercadoPagoJerseyClient implements MercadoPagoClient {

	private static final String MERCADO_PAGO_API = "https://api.mercadopago.com/v1";
	
	public MercadoPagoToken retrieveNewTokenUsing(TokenClientCredentials clientCredentials) {
		Client client = ClientBuilder.newClient();
		
		Form form = new Form();
		form.param("grant_type", "client_credentials");
		form.param("client_id", clientCredentials.getClientId());
		form.param("client_secret", clientCredentials.getClientSecret());
		
		Response response = client
			.target("https://api.mercadopago.com/oauth/token")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		MercadoPagoToken token = response.readEntity(MercadoPagoToken.class);
		
		return token;
	}

	public List<PaymentMethod> retrieveAllPaymentMethodsUsing(MercadoPagoToken token) {
		Client client = ClientBuilder.newClient();
		
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
