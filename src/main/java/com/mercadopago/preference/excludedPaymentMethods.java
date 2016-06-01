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
public class ExcludedPaymentMethods {

	@XmlElement(name = "excluded_payment_methods")
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
	@XmlElement(name = "excluded_payment_types")
	private List<ExcludedPaymentType> paymentTypes = new ArrayList<>();

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}
	
	public List<ExcludedPaymentType> getPaymentTypes() {
		return paymentTypes;
	}
	
	public void addPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethods.add(paymentMethod);
	}

	public void addPaymentType(ExcludedPaymentType paymentType) {
		this.paymentTypes.add(paymentType);
	}

	@Override
	public String toString() {
		return "excludedPaymentMethods [excludedPaymentMethods=" + paymentMethods + ", excludedPaymentTypes="
				+ paymentTypes + "]";
	}
	
}
