package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mercadopago.preference.Address;
import com.mercadopago.preference.Phone;

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
		return "PayerInformation [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", address="
				+ address + "]";
	}
	
}
