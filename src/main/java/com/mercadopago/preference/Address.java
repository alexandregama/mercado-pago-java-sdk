package com.mercadopago.preference;

import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.MoreObjects;

public class Address {

	@XmlElement(name = "zip_code")
	private String zipCode;
	
	@XmlElement(name = "street_name")
	private String streetName;
	
	@XmlElement(name = "street_number")
	private Integer streetNumber;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("zipCode", zipCode)
			.add("streetName", streetName)
			.add("streetNumber", streetNumber)
		.toString();
	}
	
}
