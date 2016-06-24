package com.mercadopago.payment;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Alexandre Gama
 * Groups the details of the transaction
 *
 */
@XmlRootElement(name = "transaction_details")
@XmlAccessorType(FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetails {

	/**
	 * External financial institution identifier (e.g.: company id for atm)
	 */
	@XmlElement(name = "financial_institution")
	private String financialInstitution;
	
	/**
	 * Amount received by the seller
	 */
	@XmlElement(name = "net_received_amount")
	private BigDecimal amountReceiveBySeller;

	public String getFinancialInstitution() {
		return financialInstitution;
	}

	public BigDecimal getAmountReceiveBySeller() {
		return amountReceiveBySeller;
	}
	
}
