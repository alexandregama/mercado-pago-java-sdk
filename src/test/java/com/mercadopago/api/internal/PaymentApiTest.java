package com.mercadopago.api.internal;

import static com.mercadopago.api.payment.OrderOnPayment.OrderType.MERCADOPAGO;
import static com.mercadopago.api.payment.PaymentRetrieved.OperationType.REGULAR_PAYMENT;
import static com.mercadopago.api.payment.PaymentRetrieved.PaymentStatus.PENDING;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Optional;
import com.mercadopago.api.oauth.MercadoPagoAuthenticationFactory;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.payment.OrderOnPayment;
import com.mercadopago.api.payment.PayerInformation;
import com.mercadopago.api.payment.PaymentAdditionalInformations;
import com.mercadopago.api.payment.PaymentItem;
import com.mercadopago.api.payment.PaymentPayer;
import com.mercadopago.api.payment.PaymentRetrieved;
import com.mercadopago.api.payment.PaymentToCreate;
import com.mercadopago.api.payment.TransactionDetails;
import com.mercadopago.api.payment.PaymentRetrieved.PaymentStatus;
import com.mercadopago.api.paymentmethod.PaymentMethod;
import com.mercadopago.api.preference.Address;
import com.mercadopago.api.preference.Phone;
import com.mercadopago.api.token.PropertiesReader;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class PaymentApiTest {

	private static MercadoPagoApi mercadoPagoApi;
	
	@BeforeClass
	public static void getCredentials() {
		String accessTokenForSandbox = new PropertiesReader().getPropertyValueFrom("access_token_sandbox");
		MercadoPagoToken token = MercadoPagoAuthenticationFactory.generateSandboxTokenUsing(accessTokenForSandbox);
		
		mercadoPagoApi = MercadoPagoApiFactory.from(token);
	}
	
	@Test
	public void shouldCreateANewPaymentWithAllRequiredFields() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		
		assertThat(paymentCreated.getId(), is(notNullValue()));
		assertThat(paymentCreated.getPaymentMethodId(), is(equalTo("pagofacil")));
		assertThat(paymentCreated.getDescription(), is(equalTo("Title of what you are paying for")));
		assertThat(paymentCreated.getInstallments(), is(equalTo(1)));
	}
	
	@Test
	public void shouldCreateANewPaymentWithAllRequiredFieldsAndReturningOperationType() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		
		assertThat(paymentCreated.getOperationType(), is(equalTo(REGULAR_PAYMENT)));
	}
	
	@Test
	public void shouldCreateANewPaymentWithAllRequiredFieldsAndOrderIdentifier() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		payment.setOrder(new OrderOnPayment(MERCADOPAGO, 10L));
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		OrderOnPayment order = paymentCreated.getOrder();
		
		assertThat(paymentCreated.getOperationType(), is(equalTo(REGULAR_PAYMENT)));
		assertThat(order.getId(), is(equalTo(10L)));
		assertThat(order.getType(), is(equalTo(MERCADOPAGO)));
	}
	
	@Test
	public void shouldCreateANewPaymentWithAllRequiredFieldsAndRetrieveAPaymentStatus() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		payment.setOrder(new OrderOnPayment(MERCADOPAGO, 10L));
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		PaymentStatus status = paymentCreated.getStatus();
		
		assertThat(status, is(equalTo(PENDING)));
		
	}
	
	@Test
	public void shouldCreateANewPaymentWithAListOfItems() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);

		PaymentAdditionalInformations additionalInformations = new PaymentAdditionalInformations();
		PaymentItem item = PaymentItem.fromId("10").withTitle("Macbook sticker").costing(TEN).fromCategory("macbook").withDescription("Awesome Macbook Sticker").build();
		additionalInformations.addItem(item);
		payment.setAdditionalInformation(additionalInformations);
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		PaymentAdditionalInformations informations = paymentCreated.getAdditionalInformation();
		
		PaymentItem paymentItem = informations.getItems().get(0);
		
		assertThat(paymentItem.getId(), is(equalTo("10")));
		assertThat(paymentItem.getTitle(), is(equalTo("Macbook sticker")));
		assertThat(paymentItem.getPrice(), is(equalTo(TEN)));
		assertThat(paymentItem.getCategory(), is(equalTo("macbook")));
		assertThat(paymentItem.getDescription(), is(equalTo("Awesome Macbook Sticker")));
	}
	
	@Test
	public void shouldCreateANewPaymentWithPayerInformation() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		PaymentAdditionalInformations additionalInformations = new PaymentAdditionalInformations();
		PaymentItem item = PaymentItem.fromId("10").withTitle("Macbook sticker").costing(TEN).fromCategory("macbook").withDescription("Awesome Macbook Sticker").build();
		additionalInformations.addItem(item);
		
		PayerInformation payerInformation = new PayerInformation("Alexandre", "Gama", new Phone("11", "985648575"), new Address("04689547", "Rua Beira Rio", 100));
		additionalInformations.setPayer(payerInformation);
		
		payment.setAdditionalInformation(additionalInformations);
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		PaymentAdditionalInformations informations = paymentCreated.getAdditionalInformation();
		
		PayerInformation payerInformationRetrieved = informations.getPayer();
		
		assertThat(payerInformationRetrieved.getFirstName(), is(equalTo("Alexandre")));
	}
	
	@Test
	public void shouldCreateANewPaymentWithAllSimpleWritableFields() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		payment.useBinaryMode();
		payment.setExternalReferenceCode("123456789");
		payment.willBeCaptured();
		payment.setNotificationUrl("http://www.elo7.com.br/notification_url");
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		
		assertTrue(paymentCreated.isInBinaryMode());
		assertThat(paymentCreated.getExternalReferenceCode(), is(equalTo("123456789")));
		assertThat(paymentCreated.getNotificationUrl(), is(equalTo("http://www.elo7.com.br/notification_url")));
		assertTrue(paymentCreated.wereCaptured());
	}
	
	@Test
	public void shouldCreateANewPaymentRetrievingAllTransactionDetails() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		TransactionDetails transactionDetails = paymentCreated.getTransactionDetails();
		
		assertThat(transactionDetails.getFinancialInstitution(), nullValue());
		assertThat(transactionDetails.getAmountReceiveBySeller(), is(equalTo(ZERO)));
		assertThat(transactionDetails.getTotalPaidAmountWithFees(), is(equalTo(TEN)));
		assertThat(transactionDetails.getInstallmentAmount(), is(equalTo(ZERO)));
		assertThat(transactionDetails.getOverpaidAmount(), is(equalTo(ZERO)));
		assertThat(transactionDetails.getExternalResourceUrl(), is(equalTo("https://sandbox.mercadopago.com/coupon/pagofacil")));
	}
	
	@Test
	public void shouldReturnAPaymentBasedOnItsId() throws Exception {
		Integer paymentId = createNewValidPayment();
		
		Optional<PaymentRetrieved> payment = mercadoPagoApi.payments().findBy(paymentId);
		
		assertThat(payment.get().getId(), is(equalTo(paymentId)));
	}
	
	@Test
	public void shouldNotReturnAPaymentBasedOnInexistentId() throws Exception {
		Integer paymentId = 12345678;
		
		Optional<PaymentRetrieved> payment = mercadoPagoApi.payments().findBy(paymentId);
		
		assertFalse(payment.isPresent());
	}

	private Integer createNewValidPayment() {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().findBy("pagofacil").get();
		
		PaymentPayer payer = new PaymentPayer();
		payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
		payer.setEmail("alexandre.gama@elo7.com");
		
		Address address = new Address();
		address.setStreetName("Rua Beira Rio");
		address.setStreetNumber(70);
		address.setZipCode("04689115");
		
		PaymentToCreate payment = new PaymentToCreate();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
		
		Integer idFromMercadoPago = paymentCreated.getId();
		
		return idFromMercadoPago;
	}
	
}
