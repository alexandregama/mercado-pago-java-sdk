package com.mercadopago.api.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;

/**
 * 
 * @author Alexandre Gama - Preference Model
 * This API allows you to set up, during the payment process, all the item information, 
 * any accepted means of payment and many other options.
 *
 */

@XmlAccessorType(FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Preference {

	@XmlElement(name = "id", required = true)
	private String id;

	/**
	 * Notice that you must send at least one Item. This is required by Mercado Pago Api
	 */
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
	private PreferencePayer payer;
	
	@XmlElement(name = "payment_methods")
	private PreferencePaymentMethods paymentMethods;
	
	@XmlElement(name = "shipments")
	private Shipment shipments;
	
	/**
	 * Origin of the payment. Default value: NONE
	 * 
	 * Data type: String(256)
	 * Mode: readable | writable
	 * 
	 */
	@XmlElement(name = "marketplace")
	private String marketplace;
	
	/**
	 * Marketplace's fee charged by application owner. Default value: 0%
	 * 
	 * Data type: Float on Mercado Pago
	 * Mode: readable | writable
	 * 
	 */
	@XmlElement(name = "marketplace_fee")
	private BigDecimal marketplaceFee;
	
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
		return Collections.unmodifiableList(items);
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

	public PreferencePayer getPayer() {
		return payer;
	}

	public void setPayer(PreferencePayer payer) {
		this.payer = payer;
	}
	
	public PreferencePaymentMethods getPaymentMethods() {
		return paymentMethods;
	}
	
	public void setPaymentMethods(PreferencePaymentMethods paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", id)
			.add("items", items)
			.add("collectorId", collectorId)
			.add("operationType", operationType)
			.add("additionalInformation", additionalInformation)
			.add("backUrl", backUrl)
			.add("payer", payer)
			.add("paymentMethods", paymentMethods)
			.add("shipments", shipments)
			.add("marketplace", marketplace)
		.toString();
	}

	public Shipment getShipments() {
		return shipments;
	}

	public void setShipments(Shipment shipments) {
		this.shipments = shipments;
	}

	public String getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
	}

	public BigDecimal getMarketplaceFee() {
		return marketplaceFee;
	}

	public void setMarketplaceFee(BigDecimal marketplaceFee) {
		this.marketplaceFee = marketplaceFee;
	}
	
}
