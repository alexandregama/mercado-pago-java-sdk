package com.mercadopago.api.service;

import com.google.common.base.Optional;
import com.mercadopago.payment.PaymentRetrieved;
import com.mercadopago.payment.PaymentToCreate;

public interface PaymentApi {

	Optional<PaymentRetrieved> findBy(Integer paymentId);

	PaymentRetrieved createNew(PaymentToCreate payment);

}
