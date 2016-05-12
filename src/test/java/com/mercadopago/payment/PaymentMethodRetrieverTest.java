package com.mercadopago.payment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.mercadopago.api.MercadoPagoClient;
import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;

public class PaymentMethodRetrieverTest {
	
	private static final Set<String> PaymentMethodsIds = new HashSet<>(Arrays.asList("visa", "master", "amex", "naranja", "nativa", 
			"tarshop", "cencosud", "cabal", "diners", "argencard", "pagofacil", "rapipago", "redlink", "bapropagos", "cargavirtual",
			"cordial", "cordobesa", "cmr"));

	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPago() throws Exception {
		MercadoPagoClient mercadoPago = new MercadoPagoJerseyClient();
		
		TokenClientCredentials clientCredentials = new TokenClientCredentialsReader().getCredentials();
		MercadoPagoToken token = mercadoPago.retrieveNewTokenUsing(clientCredentials);
		
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		List<String> methodsIds = new ArrayList<>();
		paymentAcceptedMethods.forEach(method -> methodsIds.add(method.getId()));
		
		assertThat(paymentAcceptedMethods.size(), is(equalTo(18)));
		PaymentMethodsIds.forEach(id -> assertThat(methodsIds, hasItem(id)));
	}
	
}
