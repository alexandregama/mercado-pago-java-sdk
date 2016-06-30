package com.mercadopago.api.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mercadopago.api.exception.MercadoPagoException;
import com.mercadopago.api.exception.MercadoPagoExceptionInformation;
import com.mercadopago.api.internal.MercadoPagoAccountConnectOAuthApi;
import com.mercadopago.api.internal.MercadoPagoApiFactory;
import com.mercadopago.api.internal.MercadoPagoAuthorizableApi;
import com.mercadopago.api.internal.SellerConnectAccount;
import com.mercadopago.api.oauth.MercadoPagoToken;
import com.mercadopago.api.service.MercadoPagoSellerConnectableAccount;
import com.mercadopago.api.token.MercadoPagoCredentials;
import com.mercadopago.api.token.PropertiesReader;

public class SellerConnectAccountTest {

	private static MercadoPagoCredentials credentials;
	private static MercadoPagoToken token;

	@BeforeClass
	public static void setup() {
		PropertiesReader propertiesReader = new PropertiesReader();
		String clientId = propertiesReader.getPropertyValueFrom(MercadoPagoToken.CLIENT_ID);
		String secretKey = propertiesReader.getPropertyValueFrom(MercadoPagoToken.SECRET_KEY);
		String sandboxAccessToken = propertiesReader.getPropertyValueFrom("access_token_sandbox");
		
		credentials = new MercadoPagoCredentials(clientId, secretKey);
		token = MercadoPagoApiFactory.generateSandboxTokenUsing(sandboxAccessToken);
	}
	
	@Test
	public void shouldCreateALinkToAllowsSellersToConnectWithItsOwnMercadoPagoAccount() throws Exception {
		MercadoPagoSellerConnectableAccount sellerConnectAccount = new SellerConnectAccount(credentials);
		String url = sellerConnectAccount.redirectingTo("http://www.elo7.com.br/teste");
		
		assertThat(url, is(equalTo("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=3716&response_type=code&platform_id=mp&redirect_uri=http://www.elo7.com.br/teste")));
	}
	
	@Test
	public void testName() throws Exception {
		MercadoPagoAuthorizableApi mercadoPagoOAuthApi = MercadoPagoApiFactory.authorizationFrom(credentials);
		MercadoPagoAccountConnectOAuthApi api = mercadoPagoOAuthApi.accountConnectApiUsing(token);
		try {
			MercadoPagoToken sellerCredentials = api.getSellerCredentials();
			System.out.println(sellerCredentials);
		} catch (MercadoPagoException e) {
			MercadoPagoExceptionInformation information = e.getExceptionInformation();
			System.out.println(information);
		}
	}
	
}
