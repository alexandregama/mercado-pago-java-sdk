package com.mercadopago.api;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mercadopago.api.exception.MercadoPagoCauseException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MercadoPagoExceptionInformation {

	@XmlElement(name = "message")
	private String message;

	@XmlElement(name = "error")
	private String error;

	@XmlElement(name = "status")
	private String status;

	@XmlElement(name = "cause")
	private List<MercadoPagoCauseException> causes;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MercadoPagoCauseException> getCauses() {
		return causes;
	}

	public void setCauses(List<MercadoPagoCauseException> causes) {
		this.causes = causes;
	}

	@Override
	public String toString() {
		return "MercadoPagoExceptionInformation [message=" + message + ", error=" + error + ", status=" + status
				+ ", causes=" + causes + "]";
	}

}
