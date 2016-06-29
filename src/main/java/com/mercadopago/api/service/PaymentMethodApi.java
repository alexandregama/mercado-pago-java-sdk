package com.mercadopago.api.service;

import java.util.List;
import java.util.Optional;

import com.mercadopago.api.paymentmethod.PaymentMethod;

public interface PaymentMethodApi {

	List<PaymentMethod> getAll();

	Optional<PaymentMethod> findBy(String paymentMethodId);

}
