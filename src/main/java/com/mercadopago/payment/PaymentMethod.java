package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentMethod {

	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "name")
	private String name;
	
}
