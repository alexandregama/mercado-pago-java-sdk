package com.mercadopago.api.internal;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.api.exception.MercadoPagoException;
import com.mercadopago.api.exception.MercadoPagoExceptionInformation;
import com.mercadopago.api.oauth.MercadoPagoProductionToken;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class MercadoPagoAccountConnectOAuthApi implements AccountConnectableApi {

	private String accessToken;

	public MercadoPagoAccountConnectOAuthApi(String fixedAccessToken) {
		this.accessToken = fixedAccessToken;
	}

	@Override
	public MercadoPagoUrlRedirection getMercadoPagoTokenFromSellerUsingAuthorizationCode(String authorizationCode) {
		return new MercadoPagoUrlRedirection(authorizationCode);
	}
	
	class MercadoPagoUrlRedirection {
		
		private String authorizationCode;

		public MercadoPagoUrlRedirection(String authorizationCode) {
			this.authorizationCode = authorizationCode;
		}
		
		public MercadoPagoProductionToken andRedirectingTo(String urlToRedirect) {
			Form form = new Form();
			form.param("client_secret", accessToken);
			form.param("grant_type", "authorization_code");
			form.param("code", authorizationCode);
			form.param("redirect_uri", urlToRedirect);
			
			Response response = ClientBuilder.newClient()
				.target("https://api.mercadopago.com")
				.path("oauth/token")
				.request(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, APPLICATION_FORM_URLENCODED_TYPE));
			
			if (response.getStatus() == BAD_REQUEST.getStatusCode()) {
				MercadoPagoExceptionInformation exceptionInformation = response.readEntity(MercadoPagoExceptionInformation.class);
				throw new MercadoPagoException(exceptionInformation);
			}
			MercadoPagoProductionToken sellersToken = response.readEntity(MercadoPagoProductionToken.class);
			
			return sellersToken;	
		}
	}	

}
