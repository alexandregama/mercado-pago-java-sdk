package com.mercadopago.api.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.MoreObjects;
import com.mercadopago.api.preference.Address;
import com.mercadopago.api.preference.Phone;

/**
 * @author Alexandre Gama
 * 
 * Payer Informations to be used when user is creating a new Payment and needs to save
 * a few informations about the payment.
 * 
 * You can use this model to complement additional informations that is represented by the class {@code com.mercadopago.payment.PaymentAdditionalInformations}
 * 
 */
@XmlRootElement(name = "payer")
public class PayerInformation {

	@XmlElement(name = "first_name")
	private String firstName;
	
	@XmlElement(name = "last_name")
	private String lastName;
	
	@XmlElement(name = "phone")
	private Phone phone;
	
	@XmlElement(name = "address")
	private Address address;

	public PayerInformation(String firstName, String lastName, Phone phone, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}
	
	public PayerInformation() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("firstName", this.firstName)
				.add("lastName", this.lastName)
				.add("phone", this.phone)
				.add("address", this.address)
			.toString();
	}
	
}
