package com.mercadopago.api.internal;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.internal.MercadoPagoApi;
import com.mercadopago.api.oauth.MercadoPagoAuthenticationFactory;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.paymentmethod.PaymentMethod;
import com.mercadopago.api.token.PropertiesReader;

public class PaymentMethodApiTest {
	
	private static final Set<String> PAYMENT_METHODS_IDS = new HashSet<>(asList("visa", "master", "amex", "naranja", "nativa", 
			"cencosud", "cabal", "diners", "argencard", "pagofacil", "rapipago", "redlink", "bapropagos", "cargavirtual",
			"cordial", "cordobesa", "cmr"));
	
	private static MercadoPagoToken token;
	
	private static MercadoPagoApi mercadoPagoApi;

	@BeforeClass
	public static void generateNewTokenForAllThoseTests() {
		String accessTokenForSandbox = new PropertiesReader().getPropertyValueFrom("access_token_sandbox");
		token = MercadoPagoAuthenticationFactory.generateSandboxTokenUsing(accessTokenForSandbox);
		mercadoPagoApi = new MercadoPagoJerseyApi(token);
	}
	
	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPago() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
		
		List<String> methodsIds = new ArrayList<>();
		paymentAcceptedMethods.forEach(method -> methodsIds.add(method.getId()));
		
		assertThat(paymentAcceptedMethods.size(), is(equalTo(17)));
		PAYMENT_METHODS_IDS.forEach(id -> assertThat(methodsIds, hasItem(id)));
	}
	
	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPagoAndCheckIfAllStatusesAreActive() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getStatus().getName(), is(equalTo("active"))));
	}
	
	@Test
	public void shouldCheckIfAllPaymentMethodsHaveASecureThumbnail() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getSecureThumbnail(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveAThumbnail() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getThumbnail(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveADeferredCapture() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getDeferredCapture(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveSettingsWithBinPattern() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();

		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		String pattern = paymentMethod.getSettings().get(0).getBin().getPattern();
		String exclusionPattern = paymentMethod.getSettings().get(0).getBin().getExclusionPattern();
		String installmentsPattern = paymentMethod.getSettings().get(0).getBin().getInstallmentsPattern();
		
		assertThat(pattern, is(equalTo("^4")));
		assertThat(exclusionPattern, is(equalTo("^(487017)")));
		assertThat(installmentsPattern, is(equalTo("^4")));
	}
	
	@Test
	public void shouldCheckIfExistsAdditionalInformationNeededForVisaCreditCard() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();

		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		
		assertThat(paymentMethod.getAdditionalInfoNeeded(), is(notNullValue()));
		assertThat(paymentMethod.getAdditionalInfoNeeded().size(), is(equalTo(3)));
		assertThat(paymentMethod.getAdditionalInfoNeeded().get(0), is(equalTo("cardholder_name")));
		assertThat(paymentMethod.getAdditionalInfoNeeded().get(1), is(equalTo("cardholder_identification_type")));
		assertThat(paymentMethod.getAdditionalInfoNeeded().get(2), is(equalTo("cardholder_identification_number")));
	}
	
	@Test
	public void shouldCheckIfExistsMaxAllowedAmountForVisaCreditCard() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
		
		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		
		assertThat(paymentMethod.getMinAllowedAmount(), is(equalTo(0)));
		assertThat(paymentMethod.getMaxAllowedAmount(), is(equalTo(250_000)));
	}
	
	@Test
	public void shouldCheckIfExistsAccreditationTimeForVisaCreditCard() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();

		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		
		assertThat(paymentMethod.getAccreditationTime(), is(equalTo(2_880)));
	}
	
	@Test
	public void shouldRetrieveAPaymentMethodByItsId() throws Exception {
		String paymentMethodId = "visa";
		Optional<PaymentMethod> paymentMethod = mercadoPagoApi.paymentMethods().findBy(paymentMethodId);
		
		assertTrue(paymentMethod.isPresent());
	}
	
}
