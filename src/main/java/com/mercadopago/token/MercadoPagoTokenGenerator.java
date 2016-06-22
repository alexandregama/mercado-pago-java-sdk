package com.mercadopago.token;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenCredentials;

public class MercadoPagoTokenGenerator {

	public MercadoPagoToken generateUsing(TokenCredentials credentials) {
		Client client = ClientBuilder.newClient();
		
		Form form = new Form();
		form.param("grant_type", "client_credentials");
		form.param("client_id", credentials.getClientId());
		form.param("client_secret", credentials.getClientSecret());
		
		Response response = client
			.target("https://api.mercadopago.com/oauth/token")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		MercadoPagoToken token = response.readEntity(MercadoPagoToken.class);
		
		return token;
	}

}
