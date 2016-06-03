package com.mercadopago.preference;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payer {

	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "surname")
	private String lastname;
	
	@XmlElement(name = "email")
	private String email;
	
	@XmlElement(name = "phone")
	private Phone phone;
	
	@XmlElement(name = "identification")
	private PersonalIdentification identification;
	
	@XmlElement(name = "address")
	private Address address;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public PersonalIdentification getIdentification() {
		return identification;
	}

	public void setIdentification(PersonalIdentification identification) {
		this.identification = identification;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Payer [name=" + name + ", lastname=" + lastname + ", email=" + email + ", phone=" + phone
				+ ", identification=" + identification + ", address=" + address + "]";
	}
	
}
