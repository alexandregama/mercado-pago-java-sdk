package com.mercadopago.api.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.mercadopago.api.exception.MercadoPagoException;
import com.mercadopago.api.exception.MercadoPagoExceptionInformation;
import com.mercadopago.api.internal.AccountConnectOAuthApi;
import com.mercadopago.api.internal.AuthorizationApi;
import com.mercadopago.api.internal.OAuthAuthorizationApi;
import com.mercadopago.api.internal.SellerConnectAccount;
import com.mercadopago.api.oauth.MercadoPagoAuthenticationFactory;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.SellerConnectableAccount;
import com.mercadopago.api.token.MercadoPagoCredentials;
import com.mercadopago.api.token.PropertiesReader;

public class SellerConnectAccountTest {

	private static MercadoPagoCredentials credentials;
	private static MercadoPagoToken token;

	@Before
	public void setup() {
		PropertiesReader propertiesReader = new PropertiesReader();
		String clientId = propertiesReader.getPropertyValueFrom(MercadoPagoToken.CLIENT_ID);
		String secretKey = propertiesReader.getPropertyValueFrom(MercadoPagoToken.SECRET_KEY);
		
		credentials = new MercadoPagoCredentials(clientId, secretKey);
		token = MercadoPagoAuthenticationFactory.generateSandboxTokenUsing("TEST-TOKEN");
	}
	
	@Test
	public void shouldCreateALinkToAllowsSellersToConnectWithItsOwnMercadoPagoAccount() throws Exception {
		SellerConnectableAccount sellerConnectAccount = new SellerConnectAccount(credentials);
		String url = sellerConnectAccount.redirectingTo("http://www.elo7.com.br/teste");
		
		assertThat(url, is(equalTo("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=3716&response_type=code&platform_id=mp&redirect_uri=http://www.elo7.com.br/teste")));
	}
	
	public void testName() throws Exception {
		AuthorizationApi mercadoPagoOAuthApi = new OAuthAuthorizationApi(credentials);
		AccountConnectOAuthApi api = mercadoPagoOAuthApi.accountConnectApiUsing(token);
		try {
			MercadoPagoToken sellerCredentials = api.getSellerCredentials();
			System.out.println(sellerCredentials);
		} catch (MercadoPagoException e) {
			MercadoPagoExceptionInformation information = e.getExceptionInformation();
			System.out.println(information);
		}
	}
	
}
