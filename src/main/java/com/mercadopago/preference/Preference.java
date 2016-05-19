package com.mercadopago.preference;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Preference {

	@XmlElement(name = "items")
	private List<Item> items = new ArrayList<>();

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}
	
}
