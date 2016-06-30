package com.mercadopago.api.exception;

public class MercadoPagoException extends RuntimeException {

	private static final long serialVersionUID = -5069201524272608616L;
	private MercadoPagoExceptionInformation exceptionInformation;
	
	public MercadoPagoException(String message) {
		super(message);
	}

	public MercadoPagoException(MercadoPagoExceptionInformation exceptionInformation) {
		this.exceptionInformation = exceptionInformation;
	}
	
	public MercadoPagoExceptionInformation getExceptionInformation() {
		return exceptionInformation;
	}
	
}
