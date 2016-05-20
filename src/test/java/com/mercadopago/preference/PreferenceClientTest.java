package com.mercadopago.preference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	
}
