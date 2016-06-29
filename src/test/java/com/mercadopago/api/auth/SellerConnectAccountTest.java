package com.mercadopago.api.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.internal.SellerConnectAccount;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.token.MercadoPagoCredentials;
import com.mercadopago.token.PropertiesReader;

public class SellerConnectAccountTest {

	private static MercadoPagoCredentials credentials;

	@BeforeClass
	public static void setup() {
		PropertiesReader propertiesReader = new PropertiesReader();
		String clientId = propertiesReader.getPropertyValueFrom(MercadoPagoToken.CLIENT_ID);
		String secretKey = propertiesReader.getPropertyValueFrom(MercadoPagoToken.SECRET_KEY);
		
		credentials = new MercadoPagoCredentials(clientId, secretKey);
	}
	
	@Test
	public void shouldCreateALinkToAllowsSellersToConnectWithItsOwnMercadoPagoAccount() throws Exception {
		SellerConnectableAccount sellerConnectAccount = new SellerConnectAccount(credentials);
		String url = sellerConnectAccount.redirectingTo("http://www.elo7.com.br/teste");
		
		assertThat(url, is(equalTo("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=3716&response_type=code&platform_id=mp&redirect_uri=http://www.elo7.com.br/teste")));
	}
	
}
