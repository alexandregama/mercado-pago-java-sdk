package com.mercadopago.payment;

import static javax.ws.rs.core.Response.Status.CREATED;

import java.io.IOException;
import java.io.StringWriter;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.api.MercadoPagoExceptionInformation;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.exception.MercadoPagoBadRequestException;

public class PaymentClientApi {

	private static final String MERCADO_PAGO_API = "https://api.mercadopago.com/v1";
	
	private MercadoPagoToken token;

	public PaymentClientApi(MercadoPagoToken token) {
		this.token = token;
	}

	public Payment createNew(PaymentWithRequiredFields payment) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		mapper.writerWithDefaultPrettyPrinter().writeValue(writer, payment);
		
		System.out.println(writer.toString());
		
		
		Response response = ClientBuilder.newClient()
			.target(MERCADO_PAGO_API)
			.path("payments")
			.queryParam("access_token", token.getAccessToken())
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.json(payment));
		
		if (response.getStatus() != CREATED.getStatusCode()) {
			MercadoPagoExceptionInformation internalMercadoPagoException = response.readEntity(new GenericType<MercadoPagoExceptionInformation>() {});
			System.out.println(internalMercadoPagoException);
			throw new MercadoPagoBadRequestException("An error ocurred while trying to Create a new Preference", internalMercadoPagoException.getMessage(), internalMercadoPagoException.getError());
		}
			
		Payment paymentSuccessfullyCreated = response.readEntity(Payment.class);
		
		return paymentSuccessfullyCreated;
	}

}
