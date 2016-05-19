package com.mercadopago.preference;

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
		Item item = new Item(1L, "First Produto", "First Awesome Product", "http://s3.amazon.com/mercadopago/image.png", "Music", 2, "BRL", BigDecimal.TEN);
		preference.addItem(item);
		
		mercadoPago.preferences().createPreference(preference);
	}
	
}
