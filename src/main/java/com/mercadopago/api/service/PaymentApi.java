package com.mercadopago.api.service;

import com.google.common.base.Optional;
import com.mercadopago.api.payment.PaymentRetrieved;
import com.mercadopago.api.payment.PaymentToCreate;

public interface PaymentApi {

	Optional<PaymentRetrieved> findBy(Integer paymentId);

	PaymentRetrieved createNew(PaymentToCreate payment);

}
