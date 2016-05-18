package com.mercadopago.payment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.MercadoPagoClient;
import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentialsReader;

public class PaymentMethodRetrieverTest {
	
	private static final Set<String> PaymentMethodsIds = new HashSet<>(Arrays.asList("visa", "master", "amex", "naranja", "nativa", 
			"tarshop", "cencosud", "cabal", "diners", "argencard", "pagofacil", "rapipago", "redlink", "bapropagos", "cargavirtual",
			"cordial", "cordobesa", "cmr"));
	
	private static MercadoPagoToken token;
	private static MercadoPagoClient mercadoPago;

	@BeforeClass
	public static void generateNewTokenForAllThoseTests() {
		mercadoPago = new MercadoPagoJerseyClient();
		token = mercadoPago.retrieveNewTokenUsing(new TokenClientCredentialsReader().getCredentials());
	}
	
	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPago() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		List<String> methodsIds = new ArrayList<>();
		paymentAcceptedMethods.forEach(method -> methodsIds.add(method.getId()));
		
		assertThat(paymentAcceptedMethods.size(), is(equalTo(19)));
		PaymentMethodsIds.forEach(id -> assertThat(methodsIds, hasItem(id)));
	}
	
	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPagoAndCheckIfAllStatusesAreActive() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getStatus().getName(), is(equalTo("active"))));
	}
	
	@Test
	public void shouldCheckIfAllPaymentMethodsHaveASecureThumbnail() throws Exception {
		List<PaymentMethod> paymentMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		
		paymentMethods.forEach(method -> assertThat(method.getSecureThumbnail(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveAThumbnail() throws Exception {
		List<PaymentMethod> paymentMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		
		paymentMethods.forEach(method -> assertThat(method.getThumbnail(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveADeferredCapture() throws Exception {
		List<PaymentMethod> paymentMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);
		
		paymentMethods.forEach(method -> assertThat(method.getDeferredCapture(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveSettingsWithBinPattern() throws Exception {
		List<PaymentMethod> paymentMethods = mercadoPago.retrieveAllPaymentMethodsUsing(token);

		PaymentMethod paymentMethod = paymentMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		String pattern = paymentMethod.getSettings().get(0).getBin().getPattern();
		String exclusionPattern = paymentMethod.getSettings().get(0).getBin().getExclusionPattern();
		String installmentsPattern = paymentMethod.getSettings().get(0).getBin().getInstallmentsPattern();
		
		assertThat(pattern, is(equalTo("^4")));
		assertThat(exclusionPattern, is(equalTo("^(487017)")));
		assertThat(installmentsPattern, is(equalTo("^4")));
	}
	
}
