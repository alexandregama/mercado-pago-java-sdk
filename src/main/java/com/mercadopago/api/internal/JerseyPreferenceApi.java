package com.mercadopago.api.internal;

import static javax.ws.rs.core.Response.Status.CREATED;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mercadopago.api.exception.MercadoPagoBadRequestException;
import com.mercadopago.api.exception.MercadoPagoExceptionInformation;
import com.mercadopago.api.exception.MercadoPagoNotFoundException;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.PreferenceApi;
import com.mercadopago.preference.Preference;

/**
 * 
 * @author Alexandre Gama
 * PreferenceApi implemented using Jersey
 *
 */
class JerseyPreferenceApi implements PreferenceApi {

	private final MercadoPagoToken token;

	JerseyPreferenceApi(final MercadoPagoToken token) {
		this.token = token;
	}

	public Preference createNew(Preference preference) {
		Client client = ClientBuilder.newClient();
		
		Response response = client
				.target("https://api.mercadopago.com")
				.path("checkout/preferences")
				.queryParam("access_token", token.getAccessToken())
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(preference));
		
		if (response.getStatus() != CREATED.getStatusCode()) {
			MercadoPagoExceptionInformation internalMercadoPagoException = response.readEntity(new GenericType<MercadoPagoExceptionInformation>() {});
			throw new MercadoPagoBadRequestException("An error ocurred while trying to Create a new Preference", internalMercadoPagoException.getMessage(), internalMercadoPagoException.getError());
		}
		Preference preferenceCreated = response.readEntity(Preference.class);
		
		return preferenceCreated;
	}

	public Preference findBy(String preferenceId) {
		Client client = ClientBuilder.newClient();
		
		Response response = client
			.target("https://api.mercadopago.com")
			.path("/checkout/preferences/" + preferenceId)
			.queryParam("access_token", token.getAccessToken())
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.get();
		
		if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			MercadoPagoExceptionInformation internalMercadoPagoException = response.readEntity(new GenericType<MercadoPagoExceptionInformation>() {});
			throw new MercadoPagoNotFoundException(String.format("Preference with id {} was not found.", preferenceId), internalMercadoPagoException);
		}
		
		Preference preference = response.readEntity(Preference.class);
		
		return preference;
	}
	
}
