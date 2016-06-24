package com.mercadopago.payment;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Alexandre Gama
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(FIELD)
public class PaymentPayer {
	
	/**
	 * Identification of the associated payer. Notice that here you must send the Customer Id instead of User Id
	 */
	@XmlElement(name = "id")
	private String customerId;
	
	@XmlElement(name = "email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
