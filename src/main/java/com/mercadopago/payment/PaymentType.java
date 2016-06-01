package com.mercadopago.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
	
	TICKET("ticket"), ATM("atm"), CREDIT_CARD("credit_card"), DEBIT_CARD("debit_card"), PREPAID_CARD("prepaid_card");
	
	private String name;
	
	PaymentType(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
}