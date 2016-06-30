package com.mercadopago.api.internal;

import static com.mercadopago.api.oauth.MercadoPagoToken.CLIENT_ID;
import static com.mercadopago.api.oauth.MercadoPagoToken.SECRET_KEY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.mercadopago.api.oauth.MercadoPagoProductionToken;
import com.mercadopago.api.token.MercadoPagoCredentials;
import com.mercadopago.api.token.PropertiesReader;

public class SellerConnectAccountTest {

	private static MercadoPagoAuthorizableApi authorizationApi;
	private static String fixedAccessToken;

	@BeforeClass
	public static void setup() {
		PropertiesReader propertiesReader = new PropertiesReader();
		String clientId = propertiesReader.getPropertyValueFrom(CLIENT_ID);
		String secretKey = propertiesReader.getPropertyValueFrom(SECRET_KEY);
		fixedAccessToken = propertiesReader.getPropertyValueFrom("access_token_sandbox");
		
		MercadoPagoCredentials credentials = new MercadoPagoCredentials(clientId, secretKey);
		authorizationApi = MercadoPagoApiFactory.authorizationFrom(credentials);
	}
	
	@Test
	public void shouldCreateALinkToAllowsSellersToConnectWithItsOwnMercadoPagoAccount() throws Exception {
		String url = authorizationApi.askForSellerAccountConnection().redirectingTo("http://www.elo7.com.br/teste");
		
		assertThat(url, is(equalTo("https://auth.mercadopago.com.br/authorization?access_type=offline&client_id=3716&response_type=code&platform_id=mp&redirect_uri=http://www.elo7.com.br/teste")));
	}
	
	@Ignore
	@Test
	public void shouldRetrieveMercadoPagoTokenFromSellersCredentialsUsingItsAuthorizationCode() throws Exception {
		MercadoPagoAccountConnectOAuthApi api = authorizationApi.accountConnectApiUsing(fixedAccessToken);
		MercadoPagoProductionToken sellerCredentials = api
				.getMercadoPagoTokenFromSellerUsingAuthorizationCode("TG-57751c97e4b0d077dff2f8a5-200679335")
				.andRedirectingTo("http://localhost:8080/mercado-pago-web-app/connected");
		
		assertThat(sellerCredentials.getAccessToken(), notNullValue());
		assertThat(sellerCredentials.getRefreshToken(), notNullValue());
		assertThat(sellerCredentials.getExpiresIn(), notNullValue());
		assertThat(sellerCredentials.getPublicKey(), notNullValue());
		assertThat(sellerCredentials.getScope(), notNullValue());
	}
	
}
