package com.mercadopago.payment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;

import com.mercadopago.api.MercadoPagoClient;
import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;

public class PaymentMethodRetrieverTest {

	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPago() throws Exception {
		MercadoPagoClient mercadoPago = new MercadoPagoJerseyClient();
		
		TokenClientCredentials clientCredentials = new TokenClientCredentialsReader().getCredentials();
		MercadoPagoToken token = mercadoPago.retrieveNewTokenUsing(clientCredentials);
		
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		
		assertThat(paymentAcceptedMethods.size(), is(equalTo(18)));
	}
}
