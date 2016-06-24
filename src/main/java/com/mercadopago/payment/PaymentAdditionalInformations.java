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
 *
 */
@XmlRootElement(name = "additional_info")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentAdditionalInformations {

	/**
	 * List of items to be paid
	 */
	@XmlElement(name = "items")
	private List<PaymentItem> items = new ArrayList<>();
	
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
