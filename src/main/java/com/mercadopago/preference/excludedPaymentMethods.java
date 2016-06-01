package com.mercadopago.preference;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadopago.payment.ExcludedPaymentType;
import com.mercadopago.payment.PaymentMethod;

@XmlRootElement(name = "payment_methods")
@JsonIgnoreProperties(ignoreUnknown = true)
public class excludedPaymentMethods {

	@XmlElement(name = "excluded_payment_methods")
	private List<PaymentMethod> excludedPaymentMethods = new ArrayList<>();
	
	@XmlElement(name = "excluded_payment_types")
	private List<ExcludedPaymentType> excludedPaymentTypes = new ArrayList<>();

	public List<PaymentMethod> getExcludedPaymentMethods() {
		return excludedPaymentMethods;
	}

	public void addExcludedPaymentMethod(PaymentMethod paymentMethod) {
		this.excludedPaymentMethods.add(paymentMethod);
	}

	public void addExcludedPaymentType(ExcludedPaymentType paymentType) {
		this.excludedPaymentTypes.add(paymentType);
	}

	@Override
	public String toString() {
		return "excludedPaymentMethods [excludedPaymentMethods=" + excludedPaymentMethods + ", excludedPaymentTypes="
				+ excludedPaymentTypes + "]";
	}
	
}
