package com.mercadopago.preference;

import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.MoreObjects;

public class Phone {

	@XmlElement(name = "area_code")
	private String areaCode;
	
	@XmlElement(name = "number")
	private String number;

	@Deprecated //Jersey required
	Phone() {
	}
	
	public Phone(String areaCode, String number) {
		this.areaCode = areaCode;
		this.number = number;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("areaCode", areaCode)
			.add("number", number)
		.toString();
	}
	
}
