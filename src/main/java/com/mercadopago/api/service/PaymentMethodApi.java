package com.mercadopago.api.service;

import java.util.List;
import java.util.Optional;

import com.mercadopago.api.paymentmethod.PaymentMethod;

/**
 * 
 * @author Alexandre Gama - <a href="https://github.com/alexandregama">GitHub Account</a>
 * 
 * Exposes all Payment Method Operations on Mercado Pago, as you can see 
 * in the <a href="https://www.mercadopago.com.br/developers/en/api-docs/custom-checkout/payment-methods/">Oficial Documentation</a>
 *
 */
public interface PaymentMethodApi {

	List<PaymentMethod> getAll();

	Optional<PaymentMethod> findBy(String paymentMethodId);

}
