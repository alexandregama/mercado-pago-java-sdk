package com.mercadopago.api.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.MoreObjects;

@XmlAccessorType(FIELD)
public class Phone {

	@XmlElement(name = "area_code")
	private String areaCode;
	
	@XmlElement(name = "number")
	private String number;

	@Deprecated //Jersey required
	Phone() {
	}
	
	public Phone(final String areaCode, final String number) {
		this.areaCode = areaCode;
		this.number = number;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getNumber() {
		return number;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("areaCode", areaCode)
			.add("number", number)
		.toString();
	}
	
}
