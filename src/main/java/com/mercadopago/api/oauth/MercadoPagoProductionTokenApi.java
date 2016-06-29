package com.mercadopago.api.oauth;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.token.MercadoPagoCredentials;

public class MercadoPagoProductionTokenApi implements TokenGeneratableApi {

	private MercadoPagoCredentials credentials;

	public MercadoPagoProductionTokenApi(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public MercadoPagoToken generateToken() {
		Form form = new Form();
		form.param("grant_type", "client_credentials");
		form.param("client_id", credentials.getClientId());
		form.param("client_secret", credentials.getClientSecret());
		
		Response response = ClientBuilder.newClient()
			.target("https://api.mercadopago.com/oauth/token")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		MercadoPagoProductionToken token = response.readEntity(MercadoPagoProductionToken.class);
		
		return token;
	}
	
}
