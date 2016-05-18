package com.mercadopago.payment;

import javax.xml.bind.annotation.XmlElement;

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
		return "BinSettings [pattern=" + pattern + ", exclusionPattern=" + exclusionPattern + ", installmentsPattern="
				+ installmentsPattern + "]";
	}

}
