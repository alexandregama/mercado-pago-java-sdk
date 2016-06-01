package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "excluded_payment_types")
public class ExcludedPaymentType {

	@XmlElement(name = "id")
	private PaymentType paymentType;

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}	
}
