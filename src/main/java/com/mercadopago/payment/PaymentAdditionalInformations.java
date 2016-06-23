package com.mercadopago.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "additional_info")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentAdditionalInformations {

	@XmlElement(name = "items")
	private List<PaymentItem> items = new ArrayList<>();

	public List<PaymentItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public void addItem(PaymentItem item) {
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "PaymentAdditionalInformations [items=" + items + "]";
	}

}
