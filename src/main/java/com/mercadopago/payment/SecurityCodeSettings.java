package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlElement;

public class SecurityCodeSettings {

	@XmlElement(name = "mode")
	private String mode;

	@XmlElement(name = "length")
	private String length;

	@XmlElement(name = "card_location")
	private String cardLocation;

	public String getMode() {
		return mode;
	}

	public String getLength() {
		return length;
	}

	public String getCardLocation() {
		return cardLocation;
	}

	@Override
	public String toString() {
		return "SecurityCodeSettings [mode=" + mode + ", length=" + length + ", cardLocation=" + cardLocation + "]";
	}

}
