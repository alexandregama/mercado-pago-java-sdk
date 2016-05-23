package com.mercadopago.preference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
	public void shouldCreateANewPreference() throws Exception {
		Preference preference = new Preference();
		Item item = Item
			.fromId(1L)
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
		
		assertThat(cretedItem.getId(), is(equalTo(1L)));
	}

	@Test(expected = MercadoPagoBadRequestException.class)
	public void shouldNotCreateANewPreferenceWhenUserDoesNotSendProductPrice() throws Exception {
		Preference preference = new Preference();
		Item item = new Item();
		item.setId(1L);
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
		item.setId(1L);
		item.setPrice(BigDecimal.TEN);
		item.setCategory("Music");
		item.setCurrency("BRL");
		
		preference.addItem(item);
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
	
}
