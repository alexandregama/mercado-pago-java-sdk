package com.mercadopago.token;

import static com.mercadopago.token.MercadoPagoTokenGenerator.ENVIRONMENT_MODE.SANDBOX;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MercadoPagoTokenGenerator {

	public MercadoPagoToken generateUsing(TokenCredentials credentials, ENVIRONMENT_MODE environmentMode) {
		if (environmentMode == SANDBOX) {
			MercadoPagoToken mercadoPagoToken = new MercadoPagoToken("TEST-3716-121113-7ac6b5b4f059fcc5c2e6630db047b7b8__LB_LA__-74108466");
			return mercadoPagoToken;
		}
		
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

	
	public enum ENVIRONMENT_MODE {
		SANDBOX, PRODUCTION;
	}
}
