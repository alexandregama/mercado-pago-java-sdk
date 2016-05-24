package com.mercadopago.preference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import static com.mercadopago.preference.Preference.PreferenceOperationType.REGULAR_PAYMENT;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.MercadoPagoBadRequestException;
import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;
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
		
		preference.addItem(item);
		preference.setAdditionalInformation("Elo7 - Additional Infos");
		
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
		
		assertThat(preferenceCreted.getId(), is(equalTo("1")));
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
	public void shouldGetPreferenceFromItsId() throws Exception {
		System.out.println(token.getAccessToken());
		Preference preference = new Preference();
		preference.setId("2");
		Item item = new Item();
		item.setId("100");
		item.setPrice(BigDecimal.TEN);
		item.setQuantity(3);
		preference.addItem(item);
		
		mercadoPago.preferences().createPreference(preference);
		Preference preferenceCreated = mercadoPago.preferences().getPreferenceFrom("2");
		
		System.out.println(preferenceCreated);
	}
}
