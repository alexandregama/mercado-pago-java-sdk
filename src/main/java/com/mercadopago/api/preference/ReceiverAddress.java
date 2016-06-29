package com.mercadopago.api.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Alexandre Gama - Shipping address to be used on Preference model
 *
 */

@XmlRootElement(name = "receiver_address")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(FIELD)
public class ReceiverAddress {

	/**
	 * Zipcode for Addrres Maximum length: 256
	 */
	@XmlElement(name = "zip_code")
	private String zipcode;

	/**
	 * Zipcode for Addrres Maximum length: 256
	 */
	@XmlElement(name = "street_name")
	private String streetName;

	/**
	 * Street number for Addrres Maximum length: 256
	 */
	@XmlElement(name = "street_number")
	private Integer streetNumber;

	/**
	 * Floor for Addrres Maximum length: 256
	 */
	@XmlElement(name = "floor")
	private String floor;

	/**
	 * Apartament for Addrres Maximum length: 256
	 */
	@XmlElement(name = "apartment")
	private String apartment;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

}
