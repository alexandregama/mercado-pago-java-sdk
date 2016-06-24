package com.mercadopago.api.payment;

import static com.mercadopago.payment.OrderOnPayment.OrderType.MERCADOPAGO;
import static com.mercadopago.payment.PaymentRetrieved.OperationType.REGULAR_PAYMENT;
import static com.mercadopago.payment.PaymentRetrieved.PaymentStatus.PENDING;
import static com.mercadopago.token.MercadoPagoTokenGenerator.ENVIRONMENT_MODE.SANDBOX;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.mercadopago.api.internal.MercadoPagoJerseyClient;
import com.mercadopago.payment.OrderOnPayment;
import com.mercadopago.payment.PayerInformation;
import com.mercadopago.payment.PaymentAdditionalInformations;
import com.mercadopago.payment.PaymentItem;
import com.mercadopago.payment.PaymentPayer;
import com.mercadopago.payment.PaymentRetrieved;
import com.mercadopago.payment.PaymentRetrieved.PaymentStatus;
import com.mercadopago.payment.PaymentToCreate;
import com.mercadopago.payment.TransactionDetails;
import com.mercadopago.paymentmethod.PaymentMethod;
import com.mercadopago.preference.Address;
import com.mercadopago.preference.Phone;
import com.mercadopago.token.MercadoPagoToken;
import com.mercadopago.token.MercadoPagoTokenGenerator;
import com.mercadopago.token.TokenClientCredentialsReader;
import com.mercadopago.token.TokenCredentials;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class PaymentApiTest {

	private MercadoPagoJerseyClient mercadoPagoApi;

	@Before
	public void getCredentials() {
		TokenCredentials credentials = new TokenClientCredentialsReader().getCredentialsForFile("config.properties");
		MercadoPagoTokenGenerator tokenGenerator = new MercadoPagoTokenGenerator();
		MercadoPagoToken token = tokenGenerator.generateUsing(credentials, SANDBOX);
		
		mercadoPagoApi = new MercadoPagoJerseyClient(token);
	}
	
	@Test
	public void shouldCreateANewPaymentWithAllRequiredFields() throws Exception {
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		PaymentMethod paymentMethod = mercadoPagoApi.paymentMethods().getBy("pagofacil").get();
		
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
		
	}
	
}
