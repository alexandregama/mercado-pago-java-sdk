package com.mercadopago.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@XmlAccessorType(FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Preference {

	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "items")
	private List<Item> items = new ArrayList<>();
	
	@XmlElement(name = "collector_id")
	private String collectorId;
	
	@XmlElement(name = "operation_type")
	private PreferenceOperationType operationType;
	
	@XmlElement(name = "additional_info")
	private String additionalInformation;
	
	@XmlElement(name = "back_urls")
	private PreferenceBackUrl backUrl;
	
	@XmlElement(name = "payer")
	private Payer payer;
	
	@XmlElement(name = "payment_methods")
	private excludedPaymentMethods acceptedPaymentMethods;
	
	public enum PreferenceOperationType {
		REGULAR_PAYMENT("regular_payment"), MONEY_TRANSFER("money_transfer");
		
		private String operationName;
		
		PreferenceOperationType(String name) {
			this.operationName = name;
		}
		
		@JsonValue
		public String getOperationName() {
			return operationName;
		}
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCollectorId() {
		return collectorId;
	}
	
	public PreferenceOperationType getOperationType() {
		return operationType;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public PreferenceBackUrl getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(PreferenceBackUrl backUrl) {
		this.backUrl = backUrl;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}
	
	public excludedPaymentMethods getAcceptedPaymentMethods() {
		return acceptedPaymentMethods;
	}
	
	public void setAcceptedPaymentMethods(excludedPaymentMethods acceptedPaymentMethods) {
		this.acceptedPaymentMethods = acceptedPaymentMethods;
	}

	@Override
	public String toString() {
		return "Preference [id=" + id + ", items=" + items + ", collectorId=" + collectorId + ", operationType="
				+ operationType + ", additionalInformation=" + additionalInformation + ", backUrl=" + backUrl
				+ ", payer=" + payer + ", acceptedPaymentMethods=" + acceptedPaymentMethods + "]";
	}

}
