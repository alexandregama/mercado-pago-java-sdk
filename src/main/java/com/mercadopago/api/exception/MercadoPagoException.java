package com.mercadopago.api.exception;

public class MercadoPagoException extends RuntimeException {

	private static final long serialVersionUID = -5069201524272608616L;
	
	public MercadoPagoException(String message) {
		super(message);
	}
	
}
