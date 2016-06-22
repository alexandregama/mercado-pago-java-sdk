package com.mercadopago.api.exception;

import com.google.common.base.MoreObjects;
import com.mercadopago.api.MercadoPagoException;

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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("friendlyMessage", friendlyMessage)
			.add("errorCode", errorCode)
			.add("mercadoPagoErrorMessage", mercadoPagoErrorMessage)
		.toString();
	}
	
}
