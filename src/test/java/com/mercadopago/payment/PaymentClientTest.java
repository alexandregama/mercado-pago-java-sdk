package com.mercadopago.payment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;
import com.mercadopago.paymentmethod.PaymentMethod;
import com.mercadopago.preference.Address;
import com.mercadopago.token.MercadoPagoTokenGenerator;

public class PaymentClientTest {

	private MercadoPagoJerseyClient mercadoPagoApi;

	@Before
	public void getCredentials() {
		TokenCredentials credentials = new TokenClientCredentialsReader().getCredentialsForFile("config.properties");
		MercadoPagoTokenGenerator tokenGenerator = new MercadoPagoTokenGenerator();
		MercadoPagoToken token = tokenGenerator.generateUsing(credentials);
		
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
		
		PaymentWithRequiredFields payment = new PaymentWithRequiredFields();
		payment.setDescription("Title of what you are paying for");
		payment.setTransactionAmount(BigDecimal.TEN);
		payment.setPaymentMethodId(paymentMethod.getId());
		payment.setInstallments(12);
		payment.setPayer(payer);
		
		Payment paymentCreated = mercadoPagoApi.payments().createNew(payment);
		System.out.println(paymentCreated);
		
		assertThat(paymentCreated.getId(), is(notNullValue()));
		assertThat(paymentCreated.getPaymentMethodId(), is(equalTo("pagofacil")));
		assertThat(paymentCreated.getDescription(), is(equalTo("Title of what you are paying for")));
		assertThat(paymentCreated.getInstallments(), is(equalTo(1)));
	}
	
}
