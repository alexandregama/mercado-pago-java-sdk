package com.mercadopago.preference;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadopago.payment.PaymentMethod;

@XmlRootElement(name = "payment_methods")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcceptedPaymentMethods {

	@XmlElement(name = "excluded_payment_methods")
	private List<PaymentMethod> excludedPaymentMethods = new ArrayList<>();

	public List<PaymentMethod> getExcludedPaymentMethods() {
		return excludedPaymentMethods;
	}

	public void addExcludedPaymentMethod(PaymentMethod paymentMethod) {
		this.excludedPaymentMethods.add(paymentMethod);
	}

	@Override
	public String toString() {
		return "AcceptedPaymentMethods [excludedPaymentMethods=" + excludedPaymentMethods + "]";
	}
	
}
