package com.mercadopago.api;

public class MercadoPagoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -594797374194348222L;
	
	public MercadoPagoNotFoundException(String friendlyErrorMessage, MercadoPagoExceptionInformation internalMercadoPagoException) {
		super(friendlyErrorMessage);
	}

}
