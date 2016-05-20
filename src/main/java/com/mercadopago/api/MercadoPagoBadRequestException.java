package com.mercadopago.api;

public class MercadoPagoBadRequestException extends MercadoPagoException {

	private static final long serialVersionUID = 1L;
	private String friendlyMessage;
	private String errorCode;
	private String mercadoPagoErrorMessage;
	
	public MercadoPagoBadRequestException(String friendlyMessage, String mercadoPagoErrorMessage, String errorCode) {
		super(friendlyMessage);
		this.friendlyMessage = friendlyMessage;
		this.mercadoPagoErrorMessage = mercadoPagoErrorMessage;
		this.errorCode = errorCode;
	}
	
	public String getFriendlyMessage() {
		return friendlyMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getMercadoPagoErrorMessage() {
		return mercadoPagoErrorMessage;
	}
	
}
