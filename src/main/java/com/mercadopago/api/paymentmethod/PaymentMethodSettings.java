package com.mercadopago.api.paymentmethod;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "settings")
public class PaymentMethodSettings {

	@XmlElement(name = "bin")
	private BinSettings bin;

	@XmlElement(name = "card_number")
	private CardNumberSettings cardNumber;

	@XmlElement(name = "security_code")
	private SecurityCodeSettings securityCode;

	public BinSettings getBin() {
		return bin;
	}

	public CardNumberSettings getCardNumber() {
		return cardNumber;
	}

	public SecurityCodeSettings getSecurityCode() {
		return securityCode;
	}

	@Override
	public String toString() {
		return "PaymentMethodSettings [bin=" + bin + ", cardNumber=" + cardNumber + ", securityCode=" + securityCode
				+ "]";
	}
	
}
