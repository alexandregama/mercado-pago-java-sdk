package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethod {

	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "name")
	private String name;

	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", name=" + name + "]";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
