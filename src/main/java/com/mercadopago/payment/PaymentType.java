package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlEnum;

import com.fasterxml.jackson.annotation.JsonValue;

@XmlEnum
public enum PaymentType {
	
	TICKET("ticket"), 
	ATM("atm"), 
	CREDIT_CARD("credit_card"), 
	DEBIT_CARD("debit_card"), 
	PREPAID_CARD("prepaid_card");
	
	private String id;
	
	PaymentType(String name) {
		this.id = name;
	}
	
	@JsonValue
	public String getId() {
		return id;
	}
}