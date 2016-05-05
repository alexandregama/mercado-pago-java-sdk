package com.mercadopago;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MercadoPago {

	public static MercadoPagoToken getToken(TokenClientCredentials clientCredentials) {
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

}
