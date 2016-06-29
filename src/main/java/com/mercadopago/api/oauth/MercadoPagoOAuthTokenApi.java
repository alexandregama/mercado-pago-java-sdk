package com.mercadopago.api.oauth;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.token.MercadoPagoCredentials;

public class MercadoPagoOAuthTokenApi implements OAuthTokeanableApi {

	private MercadoPagoCredentials credentials;

	public MercadoPagoOAuthTokenApi(MercadoPagoCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public MercadoPagoProductionToken generateProductionToken() {
		Form form = new Form();
		form.param("grant_type", "client_credentials");
		form.param("client_id", credentials.getClientId());
		form.param("client_secret", credentials.getSecretKey());
		
		Client client = ClientBuilder.newClient();
		Response response = client
			.target("https://api.mercadopago.com/oauth/token")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		MercadoPagoProductionToken token = response.readEntity(MercadoPagoProductionToken.class);
		
		return token;
	}

	@Override
	public MercadoPagoSandboxToken generateSandboxTokenUsing(String accessTokenUsedOnSandbox) {
		MercadoPagoSandboxToken mercadoPagoToken = new MercadoPagoSandboxToken(accessTokenUsedOnSandbox);
		
		return mercadoPagoToken;
	}
	
}
