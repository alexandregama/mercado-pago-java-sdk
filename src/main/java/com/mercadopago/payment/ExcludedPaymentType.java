package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class ExcludedPaymentType {

	@XmlElement(name = "id")
	private String paymentType;
	
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType.getName();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("paymentType", paymentType)
		.toString();
	}
	
}
