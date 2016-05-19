package com.mercadopago.preference;

import javax.xml.bind.annotation.XmlElement;

public class Item {

	@XmlElement(name = "id")
	private Long id;

	@XmlElement(name = "title")
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
