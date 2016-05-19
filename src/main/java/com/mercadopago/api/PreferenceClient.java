package com.mercadopago.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.preference.Preference;

public class PreferenceClient {

	private MercadoPagoToken token;

	public PreferenceClient(MercadoPagoToken token) {
		this.token = token;
	}

	public void createPreference(Preference preference) {
		Client client = ClientBuilder.newClient();
		
		Response response = client
				.target("https://api.mercadopago.com")
				.path("checkout/preferences")
				.queryParam("access_token", token.getAccessToken())
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(preference));
		
		System.out.println(response);
	}

}
