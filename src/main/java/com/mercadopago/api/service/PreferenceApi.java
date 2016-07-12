package com.mercadopago.api.service;

import com.mercadopago.api.preference.Preference;

/**
 * 
 * @author Alexandre Gama - <a href="https://github.com/alexandregama">GitHub Account</a>
 * 
 * Exposes all Preference Operations on Mercado Pago, as you can see 
 * in the <a href="https://www.mercadopago.com.br/developers/en/api-docs/basic-checkout/checkout-preferences/">Oficial Documentation</a>
 *
 * This API allows you to set up, during the payment process, all the item information, 
 * any accepted means of payment and many other options as you can see in the {@code Preference} class.
 *
 */

public interface PreferenceApi {

	/**
	 * As you can notice, when you create a new Preference, Mercado Pago returns to you the Preference
	 * that has created.
	 * 
	 * @param preference
	 * @return {@link Preference}
	 */
	Preference createNew(Preference preference);

	Preference findBy(String preferenceId);

}
