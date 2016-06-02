package com.mercadopago.preference;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadopago.payment.ExcludedPaymentType;
import com.mercadopago.payment.PaymentMethod;

@XmlRootElement(name = "payment_methods")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class PreferencePaymentMethods {

	@XmlElement(name = "excluded_payment_methods")
	private List<PaymentMethod> excludedPaymentMethods = new ArrayList<>();
	
	@XmlElement(name = "excluded_payment_types")
	private List<ExcludedPaymentType> paymentTypes = new ArrayList<>();
	
	@XmlElement(name = "default_payment_method_id")
	private String defaultPaymentMethodId;

	public List<PaymentMethod> getExcludedPaymentMethods() {
		return excludedPaymentMethods;
	}
	
	public List<ExcludedPaymentType> getPaymentTypes() {
		return paymentTypes;
	}
	
	public void addPaymentMethodToBeExcluded(PaymentMethod paymentMethod) {
		this.excludedPaymentMethods.add(paymentMethod);
	}

	public void addPaymentTypeToBeExcluded(ExcludedPaymentType paymentType) {
		this.paymentTypes.add(paymentType);
	}
	
	public void setDefaultPaymentMethod(String defaultPaymentMethod) {
		this.defaultPaymentMethodId = defaultPaymentMethod;
	}
	
	public String getDefaultPaymentMethod() {
		return defaultPaymentMethodId;
	}

	@Override
	public String toString() {
		return "excludedPaymentMethods [excludedPaymentMethods=" + excludedPaymentMethods + ", excludedPaymentTypes="
				+ paymentTypes + "]";
	}
	
}
