package com.mercadopago.preference;

import static com.mercadopago.payment.PaymentType.TICKET;
import static com.mercadopago.preference.Preference.PreferenceOperationType.REGULAR_PAYMENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.MercadoPagoBadRequestException;
import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;
import com.mercadopago.payment.ExcludedPaymentType;
import com.mercadopago.payment.PaymentMethod;
import com.mercadopago.token.MercadoPagoTokenGenerator;

public class PreferenceClientTest {

	private static MercadoPagoToken token;
	
	private MercadoPagoJerseyClient mercadoPago;

	@BeforeClass
	public static void generateToken() {
		TokenClientCredentials credentials = new TokenClientCredentialsReader().getCredentials();
		MercadoPagoTokenGenerator tokenGenerator = new MercadoPagoTokenGenerator();
		token = tokenGenerator.generateUsing(credentials);
	}
	
	@Before
	public void before() {
		mercadoPago = new MercadoPagoJerseyClient(token);
	}
	
	@Test
	public void shouldCreateANewPreferenceWithAllInformations() throws Exception {
		Preference preference = new Preference();
		Item item = Item
			.fromId("1")
			.withProductNamed("First Product")
			.withDescription("First Awesome Product")
			.costing(BigDecimal.TEN)
			.withQuantity(10)
			.usingPictureOnUrl("http://s3.amazon.com/mercadopago/image.png")
			.fromCategory("Music")
			.withCurrecyCode("ARS")
			.build();
		
		Payer payer = new Payer();
		
		payer.setName("Alexandre");
		payer.setLastname("Gama");
		payer.setEmail("alexandre.gama.lima@gmail.com");
		payer.setPhone(new Phone("55", "987653786"));
		
		Address address = new Address();
		address.setZipCode("04676500");
		address.setStreetNumber(40);
		address.setStreetName("First Street");
		payer.setAddress(address);
		
		preference.addItem(item);
		preference.setAdditionalInformation("Elo7 - Additional Infos");
		preference.setPayer(payer);
		
		Preference preferenceCreted = mercadoPago.preferences().createPreference(preference);
		Item cretedItem = preferenceCreted.getItems().get(0);
		
		assertThat(cretedItem.getId(), is(equalTo("1")));
		assertThat(cretedItem.getTitle(), is(equalTo("First Product")));
		assertThat(cretedItem.getDescription(), is(equalTo("First Awesome Product")));
		assertThat(cretedItem.getPrice(), is(equalTo(BigDecimal.TEN)));
		assertThat(cretedItem.getQuantity(), is(equalTo(10)));
		assertThat(cretedItem.getPictureUrl(), is(equalTo("http://s3.amazon.com/mercadopago/image.png")));
		assertThat(cretedItem.getCategory(), is(equalTo("Music")));
		assertThat(cretedItem.getCurrency(), is(equalTo("ARS")));
		
		assertThat(preferenceCreted.getId(), is(notNullValue()));
		assertThat(preferenceCreted.getCollectorId(), is(notNullValue()));
		assertThat(preferenceCreted.getOperationType(), is(equalTo(REGULAR_PAYMENT)));
		assertThat(preferenceCreted.getAdditionalInformation(), is(equalTo("Elo7 - Additional Infos")));
		
		assertThat(preferenceCreted.getBackUrl().getSuccess(), is(notNullValue()));
		assertThat(preferenceCreted.getBackUrl().getPending(), is(notNullValue()));
		assertThat(preferenceCreted.getBackUrl().getFailure(), is(notNullValue()));
		
		Payer payerFromPreference = preferenceCreted.getPayer();
		assertThat(payerFromPreference.getName(), is(equalTo("Alexandre")));
		assertThat(payerFromPreference.getLastname(), is(equalTo("Gama")));
		assertThat(payerFromPreference.getEmail(), is(equalTo("alexandre.gama.lima@gmail.com")));
		assertThat(payerFromPreference.getPhone().getAreaCode(), is(equalTo("55")));
		assertThat(payerFromPreference.getPhone().getNumber(), is(equalTo("987653786")));
		assertThat(payerFromPreference.getAddress().getStreetName(), is(equalTo("First Street")));
		assertThat(payerFromPreference.getAddress().getStreetNumber(), is(equalTo(40)));
		assertThat(payerFromPreference.getAddress().getZipCode(), is(equalTo("04676500")));
		
	}
	
	@Test
	public void shouldCreateANewPreferenceWithPreferenceId() throws Exception {
		Preference preference = new Preference();
		preference.setId("1");
		Item item = Item
				.fromId("1")
				.withProductNamed("First Produto")
				.withDescription("First Awesome Product")
				.costing(BigDecimal.TEN)
				.withQuantity(10)
				.usingPictureOnUrl("http://s3.amazon.com/mercadopago/image.png")
				.fromCategory("Music")
				.withCurrecyCode("BRL")
				.build();
		
		preference.addItem(item);
		
		Preference preferenceCreted = mercadoPago.preferences().createPreference(preference);
		Item cretedItem = preferenceCreted.getItems().get(0);
		
		assertThat(preferenceCreted.getId(), is(notNullValue()));
		assertThat(cretedItem.getId(), is(equalTo("1")));
	}

	@Test(expected = MercadoPagoBadRequestException.class)
	public void shouldNotCreateANewPreferenceWhenUserDoesNotSendProductPrice() throws Exception {
		Preference preference = new Preference();
		Item item = new Item();
		item.setId("1");
		item.setQuantity(3);
		item.setCategory("Music");
		item.setCurrency("BRL");
		
		preference.addItem(item);
		mercadoPago.preferences().createPreference(preference);
	}
	
	@Test(expected = MercadoPagoBadRequestException.class)
	public void shouldNotCreateANewPreferenceWhenUserDoesNotSendQuantity() throws Exception {
		Preference preference = new Preference();
		Item item = new Item();
		item.setId("1");
		item.setPrice(BigDecimal.TEN);
		item.setCategory("Music");
		item.setCurrency("BRL");
		
		preference.addItem(item);
		mercadoPago.preferences().createPreference(preference);
	}
	
	@Test(expected = MercadoPagoBadRequestException.class)
	public void shouldNotCreateANewPreferenceWhenDoesNotSendAtLeastOneItem() throws Exception {
		Preference preference = new Preference();
		Item item = new Item();
		item.setId("1");
		item.setPrice(BigDecimal.TEN);
		item.setCategory("Music");
		item.setCurrency("BRL");
		
		mercadoPago.preferences().createPreference(preference);
	}
	
	@Test
	public void shouldCreateANewPreferenceWhenUserSendAllMinimalInformations() throws Exception {
		Preference preference = new Preference();
		Item item = new Item();
		item.setPrice(BigDecimal.TEN);
		item.setQuantity(3);
		
		preference.addItem(item);
		mercadoPago.preferences().createPreference(preference);
	}

	@Test
	public void shouldCreateANewPreferenceWithExcludedPaymentMethod() throws Exception {
		Preference preference = new Preference();
		Item item = Item.fromId("1").costing(BigDecimal.TEN).withQuantity(3).build();
		
		PaymentMethod paymentMethodToBeExcluded = mercadoPago.paymentMethods().getBy("visa").get();
		ExcludedPaymentMethods excludedPaymentMethods = new ExcludedPaymentMethods();
		excludedPaymentMethods.addPaymentMethod(paymentMethodToBeExcluded);
		
		ExcludedPaymentType paymentTypeToBeExcluded = new ExcludedPaymentType();
		paymentTypeToBeExcluded.setPaymentType(TICKET);
		excludedPaymentMethods.addPaymentType(paymentTypeToBeExcluded);
		
		preference.addItem(item);
		preference.setExcludedPaymentMethods(excludedPaymentMethods);
		
		Preference preferenceCreated = mercadoPago.preferences().createPreference(preference);
		ExcludedPaymentMethods paymentMethod = preferenceCreated.getExcludedPaymentMethods();
		
		paymentMethod.getPaymentMethods().forEach(method -> assertThat(method.getId(), is(equalTo("visa"))));
		paymentMethod.getPaymentTypes().forEach(type -> assertThat(type.getPaymentType(), is(equalTo(TICKET.getName()))));
	}
	
}
