package com.mercadopago.api.internal;

import com.mercadopago.api.service.PaymentApi;
import com.mercadopago.api.service.PaymentMethodApi;
import com.mercadopago.api.service.PreferenceApi;

/**
 * 
 * @author Alexandre Gama - <a href="https://github.com/alexandregama">GitHub Account</a>
 * 
 * Interface to expose a few operations on Mercado Pago Api. As you can notice, it has one implementation,
 * the class {@code MercadoPagoJerseyApi} that is using Jersey implementation to call all REST operations
 * on Mercado Pago. 
 * 
 * Fell free to create another implementation of this Interface if you want.
 *
 */
public interface MercadoPagoApi {

	/**
	 * Exposes all Payment Methods Operations on Mercado Pago
	 * 
	 * @return {@link PaymentMethodApi}
	 */
	PaymentMethodApi paymentMethods();
	
	/**
	 * Exposes all Preference Operations on Mercado Pago
	 * 
	 * @return {@link PreferenceApi}
	 */
	PreferenceApi preferences();
	
	/**
	 * Exposes all Payment Operations on Mercado Pago
	 * 
	 * @return {@link PaymentApi}
	 */
	PaymentApi payments();

}