package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethod {

	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "payment_type_id")
	private PaymentType type;
	
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

	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

}
