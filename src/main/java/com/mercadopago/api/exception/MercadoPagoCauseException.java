package com.mercadopago.api.exception;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "cause")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(FIELD)
public class MercadoPagoCauseException {

	@XmlElement(name = "code")
	private Integer code;
	
	@XmlElement(name = "description")
	private String description;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MercadoPagoCauseException [code=" + code + ", description=" + description + "]";
	}

}
