package com.mercadopago.payment;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Alexandre Gama - Payment Model to be sent when create a new Payment
 * 
 * Using the following documentation: https://www.mercadopago.com.ar/developers/en/api-docs/custom-checkout/create-payments/
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentToCreate {
	
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
	@XmlElement(name = "installments", required = true)
	private Integer installments;
	
	/**
	 * Mercado Pago Description
	 * Selected quantity of installments
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "payer")
	private PaymentPayer payer;
	
	/**
	 * Mercado Pago Description
	 * Order identifier
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "order")
	private OrderOnPayment order;
	
	/**
	 * Mercado Pago Description
	 * When set to true, the payment can only be approved or rejected. Otherwise in_process status is added
	 * Mode readable | writable
	 */
	@XmlElement(name = "binary_mode")
	private Boolean binaryMode;
	
	/**
	 * Mercado Pago Description
	 * Determines if the payment should be captured (true, default value) or just reserved (false)
	 * Notice that the Payment retrieved will use the captured field
	 * Mode writable
	 */
	@XmlElement(name = "capture")
	private Boolean capture;
	
	/**
	 * Mercado Pago Description
	 * ID given by the merchant in their system
	 * Mode readable | writable
	 */
	@XmlElement(name = "external_reference")
	private String externalReferenceCode;
	
	/**
	 * Mercado Pago Description
	 * Amount of the coupon discount
	 * Mode readable | writable
	 */
	@XmlElement(name = "coupon_amount")
	private BigDecimal couponAmount;

	/**
	 * Mercado Pago Description
	 * URL where mercadopago will send notifications associated to changes in this payment
	 * Mode readable | writable
	 */
	@XmlElement(name = "notification_url", required = true)
	private String notificationUrl;
	
	@XmlElement(name = "additional_info")
	private PaymentAdditionalInformations additionalInformation;
	
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

	public OrderOnPayment getOrder() {
		return order;
	}

	public void setOrder(OrderOnPayment order) {
		this.order = order;
	}

	public PaymentAdditionalInformations getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(PaymentAdditionalInformations additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Override
	public String toString() {
		return "PaymentToCreate [transactionAmount=" + transactionAmount + ", paymentMethodId=" + paymentMethodId
				+ ", description=" + description + ", installments=" + installments + ", payer=" + payer + ", order="
				+ order + ", additionalInformation=" + additionalInformation + "]";
	}

	public Boolean isInBinaryMode() {
		return binaryMode;
	}


	public void useBinaryMode() {
		this.binaryMode = true;
	}

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReference) {
		this.externalReferenceCode = externalReference;
	}

	public boolean getCapture() {
		return capture;
	}

	public void willBeCaptured() {
		this.capture = true;
	}
	
	public void willNotBeCaptured() {
		this.capture = false;
	}

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

}
