package com.mercadopago.api.paymentmethod;

import java.util.List;
import java.util.Optional;

import com.mercadopago.paymentmethod.PaymentMethod;

public interface PaymentMethodApi {

	List<PaymentMethod> getAll();

	Optional<PaymentMethod> findBy(String paymentMethodId);

}
