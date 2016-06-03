package com.mercadopago.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexandre Gama
 * Shipments information
 *
 * Mercado Pago Description:
 * - Data type: Object
 * - Mode readable | writable
 */
@XmlRootElement(name = "shipments")
@XmlAccessorType(FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipment {

	@XmlElement(name = "mode")
	private Mode mode;
	
	/**
	 * Mercado Pago Description
	 * The payer have the option to pick up the shipment in your store (mode:me2 only)
	 * Mode readable | writable
	 */
	@XmlElement(name = "local_pickup")
	private boolean localPickup;

	/**
	 * Mercado Pago Description
	 * Dimensions of the shipment in cm x cm x cm, gr (mode:me2 only)
	 * Mode readable | writable
	 */
	@XmlElement(name = "dimensions")
	private String dimensions;
	
	/**
	 * Mercado Pago Description
	 * Select default shipping method in checkout (mode:me2 only)
	 * Mode readable | writable
	 */
	@XmlElement(name = "default_shipping_method")
	private Integer defaultShippingMethod;
	
	/**
	 * Mercado Pago Description
	 * Shipment cost (mode:custom only)
	 * Mode readable | writable
	 */
	@XmlElement(name = "cost")
	private BigDecimal cost;
	
	/**
	 * Mercado Pago Description
	 * Free shipping for mode:custom
	 * Mode readable | writable
	 */
	@XmlElement(name = "free_shipping")
	private boolean freeShipping;
	
	/**
	 * Mercado Pago Description
	 * Shipping address
	 * Mode readable | writable
	 */
	@XmlElement(name = "receiver_address")
	private ReceiverAddress receiverAddress;
	
	public enum Mode {
		
		CUSTOM("custom", "Custom shipping that you can use if you have a manual shipping for example"), 
		ME2("me2", "Shipping by Mercado Envios"), 
		NOT_SPECIFIED("not_specified", "Shipping mode not specified");
		
		private final String value;
		private final String description;

		Mode(final String value, final String description) {
			this.value = value;
			this.description = description;
		}
		
		@JsonValue
		public String getValue() {
			return value;
		}
		
		public String getDescription() {
			return description;
		}
	}
	
	public Mode getMode() {
		return mode;
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public boolean isUsingLocalPickup() {
		return localPickup;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public Integer getDefaultShippingMethod() {
		return defaultShippingMethod;
	}

	public void setDefaultShippingMethod(Integer defaultShippingMethod) {
		this.defaultShippingMethod = defaultShippingMethod;
	}

	@Override
	public String toString() {
		return "Shipments [mode=" + mode + ", localPickup=" + localPickup + ", dimensions=" + dimensions
				+ ", defaultShippingMethod=" + defaultShippingMethod + "]";
	}

	public void usingLocalPickup() {
		this.localPickup = true;
	}

	public void notUsingLocalPickup() {
		this.localPickup = false;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public void usingFreeShipping() {
		this.freeShipping = true;
	}

	public Boolean isUsingFreeShipping() {
		return this.freeShipping;
	}

	public void notUsingFreeShipping() {
		this.freeShipping = false;
	}

	public ReceiverAddress getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(ReceiverAddress receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

}
