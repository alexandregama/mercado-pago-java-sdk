package com.mercadopago.paymentmethod;

import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.MoreObjects;

public class BinSettings {

	@XmlElement(name = "pattern")
	private String pattern;

	@XmlElement(name = "exclusion_pattern")
	private String exclusionPattern;

	@XmlElement(name = "installments_pattern")
	private String installmentsPattern;

	public String getPattern() {
		return pattern;
	}

	public String getExclusionPattern() {
		return exclusionPattern;
	}

	public String getInstallmentsPattern() {
		return installmentsPattern;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("pattern", pattern)
			.add("exclusionPattern", exclusionPattern)
			.add("installmentsPattern", installmentsPattern)
		.toString();
	}

}
