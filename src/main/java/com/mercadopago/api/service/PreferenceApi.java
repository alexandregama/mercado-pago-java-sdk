package com.mercadopago.api.service;

import com.mercadopago.preference.Preference;

public interface PreferenceApi {

	Preference createNew(Preference preference);

	Preference findBy(String preferenceId);

}
