package com.mercadopago.preference;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.MoreObjects;

@XmlAccessorType(FIELD)
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
		return MoreObjects.toStringHelper(this)
			.add("success", success)
			.add("pending", pending)
			.add("failure", failure)
		.toString();
	}

	
}
