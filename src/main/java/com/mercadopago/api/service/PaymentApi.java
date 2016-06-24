package com.mercadopago.api.service;

import static javax.ws.rs.core.Response.Status.CREATED;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mercadopago.api.exception.MercadoPagoBadRequestException;
import com.mercadopago.api.exception.MercadoPagoExceptionInformation;
import com.mercadopago.payment.PaymentRetrieved;
import com.mercadopago.payment.PaymentToCreate;
import com.mercadopago.token.MercadoPagoToken;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class PaymentApi {

	private static final String MERCADO_PAGO_API = "https://api.mercadopago.com/v1";
	
	private MercadoPagoToken token;

	public PaymentApi(MercadoPagoToken token) {
		this.token = token;
	}

	public PaymentRetrieved createNew(PaymentToCreate payment) {
		Response response = ClientBuilder.newClient()
			.target(MERCADO_PAGO_API)
			.path("payments")
			.queryParam("access_token", token.getAccessToken())
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.json(payment));
		
		if (response.getStatus() != CREATED.getStatusCode()) {
			MercadoPagoExceptionInformation internalMercadoPagoException = response.readEntity(new GenericType<MercadoPagoExceptionInformation>() {});
			throw new MercadoPagoBadRequestException("An error ocurred while trying to Create a new Preference", internalMercadoPagoException.getMessage(), internalMercadoPagoException.getError());
		}
			
		PaymentRetrieved paymentSuccessfullyCreated = response.readEntity(PaymentRetrieved.class);
		
		return paymentSuccessfullyCreated;
	}

}
