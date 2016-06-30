package com.mercadopago.api.paymentmethod;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
	
	TICKET("ticket"), ATM("atm"), CREDIT_CARD("credit_card"), DEBIT_CARD("debit_card"), PREPAID_CARD("prepaid_card");
	
	private final String name;
	
	PaymentType(final String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
}