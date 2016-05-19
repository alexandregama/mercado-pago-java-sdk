package com.mercadopago.preference;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.MercadoPagoJerseyClient;
import com.mercadopago.api.MercadoPagoToken;
import com.mercadopago.api.TokenClientCredentials;
import com.mercadopago.api.TokenClientCredentialsReader;

public class PreferenceTest {

	private static MercadoPagoJerseyClient mercadoPago;
	private static MercadoPagoToken token;

	@BeforeClass
	public static void generateToken() {
		mercadoPago = new MercadoPagoJerseyClient();
		TokenClientCredentials credentials = new TokenClientCredentialsReader().getCredentials();
		token = mercadoPago.retrieveNewTokenUsing(credentials);
	}
	
	@Test
	public void shouldCreateANewPreference() throws Exception {
		Preference preference = new Preference();
		Item item = new Item(1L, "First Produto", "First Awesome Product", "http://s3.amazon.com/mercadopago/image.png", "Music", 2, "BRL", BigDecimal.TEN);
		preference.addItem(item);
		
		mercadoPago.createPreference(preference, token);
	}
	
}
