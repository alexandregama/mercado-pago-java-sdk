package com.mercadopago.api.internal;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.api.exception.MercadoPagoException;
import com.mercadopago.api.exception.MercadoPagoExceptionInformation;
import com.mercadopago.api.oauth.MercadoPagoProductionToken;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.AccountConnectApi;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class AccountConnectOAuthApi implements AccountConnectApi {
	
	@Override
	public MercadoPagoToken getSellerCredentials() {
		Form form = new Form();
		form.param("client_secret", "TEST-8745648399028232-121112-ceb22b63e13a380f5768440f243bad67__LC_LB__-12345678");
		form.param("grant_type", "authorization_code");
		form.param("code", "TG-5773be97e4b0d077dfccebdb-200679335");
		form.param("redirect_uri", "http://localhost:8080/mercado-pago-web-app/connected");
		
		Response response = ClientBuilder.newClient()
			.target("https://api.mercadopago.com")
			.path("oauth/token")
			.request(MediaType.APPLICATION_FORM_URLENCODED)
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		if (response.getStatus() == BAD_REQUEST.getStatusCode()) {
			MercadoPagoExceptionInformation exceptionInformation = response.readEntity(MercadoPagoExceptionInformation.class);
			throw new MercadoPagoException(exceptionInformation);
		}
		MercadoPagoProductionToken sellersToken = response.readEntity(MercadoPagoProductionToken.class);
		
		return sellersToken;
	}

	public static void main(String[] args) {
		AccountConnectOAuthApi api = new AccountConnectOAuthApi();
		try {
			MercadoPagoToken sellerCredentials = api.getSellerCredentials();
			System.out.println(sellerCredentials);
		} catch (MercadoPagoException e) {
			MercadoPagoExceptionInformation information = e.getExceptionInformation();
			System.out.println(information);
		}
	}
	
}
