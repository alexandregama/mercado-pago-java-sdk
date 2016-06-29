package com.mercadopago.api.payment;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonValue;

public class OrderOnPayment {

	@XmlElement(name = "type")
	private OrderType type;
	
	@XmlElement(name = "id")
	private Long id;
	
	public OrderOnPayment(OrderType type, Long id) {
		this.type = type;
		this.id = id;
	}
	
	public OrderOnPayment() {
	}

	public enum OrderType {
		
		MERCADOPAGO("mercadopago"), MERCADOLIBRE("mercadolibre");
		
		private String name;
		
		OrderType(String name) {
			this.name = name;
		}

		@JsonValue
		public String getName() {
			return name;
		}
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
