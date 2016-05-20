package com.mercadopago.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mercadopago.preference.Preference;

public class PreferenceClient {

	private MercadoPagoToken token;

	public PreferenceClient(MercadoPagoToken token) {
		this.token = token;
	}

	public Preference createPreference(Preference preference) {
		Client client = ClientBuilder.newClient();
		
		Response response = client
				.target("https://api.mercadopago.com")
				.path("checkout/preferences")
				.queryParam("access_token", token.getAccessToken())
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(preference));
		
		if (response.getStatus() != Status.CREATED.getStatusCode()) {
			MercadoPagoExceptionInformation internalMercadoPagoEception = response.readEntity(new GenericType<MercadoPagoExceptionInformation>() {});
			throw new MercadoPagoBadRequestException("An error ocurred while trying to Create a new Preference", internalMercadoPagoEception.getMessage(), internalMercadoPagoEception.getError());
		}
		return preference;
	}

}
