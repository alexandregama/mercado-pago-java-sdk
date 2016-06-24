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
	
	/**
	 * Total amount paid by the buyer (includes fees)
	 */
	@XmlElement(name = "total_paid_amount")
	private BigDecimal totalPaidAmountWithFees;
	
	/**
	 * Total installments amount
	 */
	@XmlElement(name = "installment_amount")
	private BigDecimal installmentAmount;
	
	/**
	 * Amount overpaid (only for tickets)
	 */
	@XmlElement(name = "overpaid_amount")
	private BigDecimal overpaidAmount;
	
	/**
	 * Identifies the resource in the payment processor
	 */
	@XmlElement(name = "external_resource_url")
	private String externalResourceUrl;

	public String getFinancialInstitution() {
		return financialInstitution;
	}

	public BigDecimal getAmountReceiveBySeller() {
		return amountReceiveBySeller;
	}

	public BigDecimal getTotalPaidAmountWithFees() {
		return totalPaidAmountWithFees;
	}

	public BigDecimal getInstallmentAmount() {
		return installmentAmount;
	}

	public BigDecimal getOverpaidAmount() {
		return overpaidAmount;
	}

	public String getExternalResourceUrl() {
		return externalResourceUrl;
	}

}
