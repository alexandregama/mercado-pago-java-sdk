package com.mercadopago.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Alexandre Gama
 * Data that could improve fraud analysis and conversion rates. Try to send as much information as possible.
 * You can use it on you Payment that is being created, using the model {@code com.mercadopago.payment.PaymentToCreate}
 */
@XmlRootElement(name = "additional_info")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentAdditionalInformations {

	/**
	 * List of items to be paid
	 * Here you can save a few characteristics about the item that are being send by the user
	 * This is not a required field but is recommendable that you send it  
	 */
	@XmlElement(name = "items")
	private List<PaymentItem> items = new ArrayList<>();
	
	/**
	 * A few informations about the Customer that is purchasing a new Item from you store
	 * This is not a required field but is recommendable that you send it
	 */
	@XmlElement(name = "payer")
	private PayerInformation payer;

	public List<PaymentItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public void setItems(List<PaymentItem> items) {
		this.items = items;
	}
	
	public void addItem(PaymentItem item) {
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "PaymentAdditionalInformations [items=" + items + "]";
	}

	public PayerInformation getPayer() {
		return payer;
	}

	public void setPayer(PayerInformation payer) {
		this.payer = payer;
	}

}
