package com.mercadopago.preference;

import javax.xml.bind.annotation.XmlElement;

public class PreferenceBackUrl {

	@XmlElement(name = "success")
	private String success;

	@XmlElement(name = "pending")
	private String pending;

	@XmlElement(name = "failure")
	private String failure;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
	}

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}

	@Override
	public String toString() {
		return "PreferenceBackUrl [success=" + success + ", pending=" + pending + ", failure=" + failure + "]";
	}

	
}
