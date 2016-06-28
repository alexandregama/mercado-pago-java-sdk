package com.mercadopago.api.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.internal.SellerConnectAccount;
import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.token.MercadoPagoToken;
import com.mercadopago.token.PropertiesReader;

public class SellerConnectAccountTest {

	private static String clientId;

	@BeforeClass
	public static void setup() {
		PropertiesReader propertiesReader = new PropertiesReader();
		clientId = propertiesReader.getPropertyValueFrom(MercadoPagoToken.CLIENT_ID);
	}
	
	@Test
	public void shouldCreateALinkToAllowsSellersToConnectWithItsOwnMercadoPagoAccount() throws Exception {
		SellerConnectableAccount sellerConnectAccount = new SellerConnectAccount(clientId);
		String url = sellerConnectAccount.redirectingTo("http://www.elo7.com.br");
		
		assertThat(url, is(equalTo("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=3716&response_type=code&platform_id=mp&redirect_uri=http://www.elo7.com.br")));
	}
	
}
