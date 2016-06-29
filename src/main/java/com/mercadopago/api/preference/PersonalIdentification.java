package com.mercadopago.api.preference;

import javax.xml.bind.annotation.XmlElement;

public class PersonalIdentification {

	@XmlElement(name = "type")
	private String type;
	
	@XmlElement(name = "number")
	private String number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PersonalIdentification [type=" + type + ", number=" + number + "]";
	}
	
}
