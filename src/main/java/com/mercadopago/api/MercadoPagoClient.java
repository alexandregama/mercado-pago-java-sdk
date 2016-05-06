package com.mercadopago.api;

public interface MercadoPagoClient {

	MercadoPagoToken retrieveNewTokenUsing(TokenClientCredentials clientCredentials);

}
