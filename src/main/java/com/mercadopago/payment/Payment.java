package com.mercadopago.payment;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Alexandre Gama - Payment Model
 * 
 * Using the following documentation: https://www.mercadopago.com.ar/developers/en/api-docs/custom-checkout/create-payments/
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
	
	/**
	 * Mercado Pago Description
	 * Payment id
	 * Mode readable
	 * Required Field
	 */
	@XmlElement(name = "id")
	private Integer id;
	
	/**
	 * Mercado Pago Description
	 * Product cost
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "transaction_amount", required = true)
	private BigDecimal transactionAmount;

	/**
	 * Mercado Pago Description
	 * Payment method chosen to do the payment
	 * Mode readable | writable
	 * https://api.mercadopago.com/sites/:site_id/payment_methods
	 * Required Field
	 */
	@XmlElement(name = "payment_method_id", required = true)
	private String paymentMethodId;
	
	/**
	 * Mercado Pago Description
	 * Payment reason or item title
	 * Mode readable | writable
	 * https://api.mercadopago.com/sites/:site_id/payment_methods
	 * Required Field
	 */
	@XmlElement(name = "description")
	private String description;
	
	/**
	 * Mercado Pago Description
	 * Selected quantity of installments
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "installments")
	private Integer installments;
	
	/**
	 * Mercado Pago Description
	 * Selected quantity of installments
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "payer")
	private PaymentPayer payer;

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public PaymentPayer getPayer() {
		return payer;
	}

	public void setPayer(PaymentPayer payer) {
		this.payer = payer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", transactionAmount=" + transactionAmount + ", paymentMethodId=" + paymentMethodId
				+ ", description=" + description + ", installments=" + installments + ", payer=" + payer + "]";
	}

}
