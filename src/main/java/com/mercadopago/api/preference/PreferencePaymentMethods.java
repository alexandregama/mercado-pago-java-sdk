package com.mercadopago.api.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import com.mercadopago.api.paymentmethod.ExcludedPaymentType;
import com.mercadopago.api.paymentmethod.PaymentMethod;

@XmlRootElement(name = "payment_methods")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(FIELD)
public class PreferencePaymentMethods {

	@XmlElement(name = "excluded_payment_methods")
	private List<PaymentMethod> excludedPaymentMethods = new ArrayList<>();
	
	@XmlElement(name = "excluded_payment_types")
	private List<ExcludedPaymentType> paymentTypes = new ArrayList<>();
	
	@XmlElement(name = "default_payment_method_id")
	private String defaultPaymentMethodId;
	
	@XmlElement(name = "installments")
	private Integer maximumInstallmentsAllowed;
	
	@XmlElement(name = "default_installments")
	private Integer preferedInstallmentsForCreditCard;

	public List<PaymentMethod> getExcludedPaymentMethods() {
		return Collections.unmodifiableList(excludedPaymentMethods);
	}
	
	public List<ExcludedPaymentType> getPaymentTypes() {
		return Collections.unmodifiableList(paymentTypes);
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

	public Integer getMaximumInstallmentsAllowed() {
		return maximumInstallmentsAllowed;
	}

	public void setMaximumInstallmentsAllowed(Integer maximumInstallmentsAllowed) {
		this.maximumInstallmentsAllowed = maximumInstallmentsAllowed;
	}

	public Integer getPreferedInstallmentsForCreditCard() {
		return preferedInstallmentsForCreditCard;
	}

	public void setPreferedInstallmentsForCreditCard(Integer preferedInstallmentsForCreditCard) {
		this.preferedInstallmentsForCreditCard = preferedInstallmentsForCreditCard;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("excludedPaymentMethods", excludedPaymentMethods)
			.add("paymentTypes", paymentTypes)
			.add("defaultPaymentMethodId", defaultPaymentMethodId)
			.add("maximumInstallmentsAllowed", maximumInstallmentsAllowed)
			.add("preferedInstallmentsForCreditCard", preferedInstallmentsForCreditCard)
		.toString();	
	}
	
}
