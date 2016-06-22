package com.mercadopago.paymentmethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.MercadoPagoClientApi;
import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;
import com.mercadopago.token.MercadoPagoTokenGenerator;

public class PaymentMethodClientApiTest {
	
	private static final Set<String> PaymentMethodsIds = new HashSet<>(Arrays.asList("visa", "master", "amex", "naranja", "nativa", 
			"tarshop", "cencosud", "cabal", "diners", "argencard", "pagofacil", "rapipago", "redlink", "bapropagos", "cargavirtual",
			"cordial", "cordobesa", "cmr"));
	
	private static MercadoPagoToken token;

	private MercadoPagoClientApi mercadoPago;

	@BeforeClass
	public static void generateNewTokenForAllThoseTests() {
		TokenClientCredentials credentials = new TokenClientCredentialsReader().getCredentialsForFile("config.properties");
		MercadoPagoTokenGenerator tokenGenerator = new MercadoPagoTokenGenerator();
		token = tokenGenerator.generateUsing(credentials);
		System.out.println(token);
		
	}
	
	@Before
	public void before() {
		mercadoPago = new MercadoPagoJerseyClient(token);
	}
	
	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPago() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();
		
		List<String> methodsIds = new ArrayList<>();
		paymentAcceptedMethods.forEach(method -> methodsIds.add(method.getId()));
		
		assertThat(paymentAcceptedMethods.size(), is(equalTo(19)));
		PaymentMethodsIds.forEach(id -> assertThat(methodsIds, hasItem(id)));
	}
	
	@Test
	public void shouldRetrieveAllAcceptedPaymentMethodsFromMercadoPagoAndCheckIfAllStatusesAreActive() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getStatus().getName(), is(equalTo("active"))));
	}
	
	@Test
	public void shouldCheckIfAllPaymentMethodsHaveASecureThumbnail() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getSecureThumbnail(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveAThumbnail() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getThumbnail(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveADeferredCapture() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();
		
		paymentAcceptedMethods.forEach(method -> assertThat(method.getDeferredCapture(), is(notNullValue())));
	}

	@Test
	public void shouldCheckIfAllPaymentMethodsHaveSettingsWithBinPattern() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();

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
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();

		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		
		assertThat(paymentMethod.getAdditionalInfoNeeded(), is(notNullValue()));
		assertThat(paymentMethod.getAdditionalInfoNeeded().size(), is(equalTo(3)));
		assertThat(paymentMethod.getAdditionalInfoNeeded().get(0), is(equalTo("cardholder_name")));
		assertThat(paymentMethod.getAdditionalInfoNeeded().get(1), is(equalTo("cardholder_identification_type")));
		assertThat(paymentMethod.getAdditionalInfoNeeded().get(2), is(equalTo("cardholder_identification_number")));
	}
	
	@Test
	public void shouldCheckIfExistsMaxAllowedAmountForVisaCreditCard() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();
		
		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		
		assertThat(paymentMethod.getMinAllowedAmount(), is(equalTo(0)));
		assertThat(paymentMethod.getMaxAllowedAmount(), is(equalTo(250_000)));
	}
	
	@Test
	public void shouldCheckIfExistsAccreditationTimeForVisaCreditCard() throws Exception {
		List<PaymentMethod> paymentAcceptedMethods = mercadoPago.paymentMethods().getListOfAllPaymentMethods();

		PaymentMethod paymentMethod = paymentAcceptedMethods.stream().filter(method -> method.getId().equals("visa")).findFirst().get();
		
		assertThat(paymentMethod.getAccreditationTime(), is(equalTo(2_880)));
	}
	
	@Test
	public void shouldRetrieveAPaymentMethodByItsId() throws Exception {
		String paymentMethodId = "visa";
		Optional<PaymentMethod> paymentMethod = mercadoPago.paymentMethods().getBy(paymentMethodId);
		
		assertTrue(paymentMethod.isPresent());
	}
	
}
