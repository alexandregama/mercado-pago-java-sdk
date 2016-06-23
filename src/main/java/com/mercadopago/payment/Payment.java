package com.mercadopago.payment;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexandre Gama - Payment Model
 * 
 * Using the following documentation: https://www.mercadopago.com.ar/developers/en/api-docs/custom-checkout/create-payments/
 * This class will be used to retrive a payment based on its id or after creating another one
 */

@XmlAccessorType(FIELD)
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
	
	/**
	 * Mercado Pago Description
	 * Payment type
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "operation_type")
	private OperationType operationType;
	
	/**
	 * Mercado Pago Description
	 * Order identifier
	 * Mode readable | writable
	 * Required Field
	 */
	@XmlElement(name = "order")
	private OrderOnPayment order;
	
	public enum OperationType {
		
		POS_PAYMENT("pos_payment", "Payment done through a Point Of Sale"), 
		CELLPHONE_RECHARGE("cellphone_recharge", "Recharge of a user's cellphone account"), 
		PAYMENT_ADDITION("payment_addition", "Addition of money to an existing payment, done in MercadoPago's site"), 
		RECURRING_PAYMENT("recurring_payment", "Automatic recurring payment due to an active user subscription"), 
		MONEY_TRANSFER("money_transfer", "Funds transfer between two users"), 
		REGULAR_PAYMENT("regular_payment", "Typification by default of a purchase being paid using MercadoPago"),
		ACCOUNT_FUND("account_fund", "Money income in the user's account");
		
		private String name;
		private String oficialDescription;
		
		OperationType(String name, String oficialDescription) {
			this.name = name;
			this.oficialDescription = oficialDescription;
		}
		
		@JsonValue
		public String getName() {
			return name;
		}
		
		public String getOficialDescription() {
			return oficialDescription;
		}
	}

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

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public OrderOnPayment getOrder() {
		return order;
	}

	public void setOrder(OrderOnPayment order) {
		this.order = order;
	}

}
