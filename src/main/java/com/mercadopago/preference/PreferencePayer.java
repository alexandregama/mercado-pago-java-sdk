package com.mercadopago.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexandre Gama
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(FIELD)
public class PreferencePayer {

	/**
	 * Identification type of the associated payer
	 * customer  : Payer is a Customer and belongs to the collector
	 * registered: The account corresponds to a MercadoPago registered user
	 * guest     : The payer doesn't have an account
	 */
	@XmlElement(name = "type")
	private PayerType payerType;
	
	/**
	 * Identification of the associated payer. Notice that here you must send the Customer Id instead of User Id
	 */
	@XmlElement(name = "id")
	private Integer customerId;
	
	@XmlElement(name = "name", nillable = true)
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
	
	public enum PayerType {
		CUSTOMER("customer"), REGISTERED("registered"), GUEST("guest");
		
		private String name;

		PayerType(String name) {
			this.name = name;
		}
		
		@JsonValue
		public String getName() {
			return name;
		}
		
	}
	
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer id) {
		this.customerId = id;
	}

	public PayerType getPayerType() {
		return payerType;
	}

	public void setPayerType(PayerType payerType) {
		this.payerType = payerType;
	}
	
}
