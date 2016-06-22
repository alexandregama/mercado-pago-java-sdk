# Mercado Pago Java SDK


[![Stories in Ready](https://badge.waffle.io/alexandregama/mercado-pago-java-sdk.png?label=ready&title=Ready)](http://waffle.io/alexandregama/mercado-pago-java-sdk)

### Payment Methods

[API Documentation](https://www.mercadopago.com.br/developers/en/api-docs/custom-checkout/payment-methods/)

**Making a call using curl**

```bash
$ curl -X GET 
  -H "Accept: application/json" 
  -H "Cache-Control: no-cache" 
  -H "Postman-Token: e7972276-b54a-1faf-6af3-4567c254e37c" "https://api.mercadopago.com/v1/payment_methods?access_token=APP_USR-3716-121113-b915e0db7344d0f66a91010a4ce5e141__LA_LC__-74108466"
```

**Making a call using wget**

```bash
$ wget --quiet \
  --method GET \
  --header 'accept: application/json' \
  --header 'cache-control: no-cache' \
  --header 'postman-token: 1ccaee93-9de3-26f6-fe6b-502cd7952205' \
  --output-document \
  - 'https://api.mercadopago.com/v1/payment_methods?access_token=APP_USR-3716-121113-b915e0db7344d0f66a91010a4ce5e141__LA_LC__-74108466'
```

Complete JSON Response from Pyament Method API

```json
[
  {
    "id": "visa",
    "name": "Visa",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/visa.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^4",
          "exclusion_pattern": "^(487017)",
          "installments_pattern": "^4"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_type",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "master",
    "name": "Mastercard",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/master.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^5",
          "exclusion_pattern": "^(589562)",
          "installments_pattern": "^5"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_identification_number",
      "cardholder_identification_type",
      "cardholder_name",
      "issuer_id"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "amex",
    "name": "American Express",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/amex.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/amex.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^((34)|(37))",
          "exclusion_pattern": null,
          "installments_pattern": "^((34)|(37))"
        },
        "card_number": {
          "length": 15,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 4,
          "card_location": "front"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_identification_number",
      "cardholder_identification_type",
      "cardholder_name"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "naranja",
    "name": "Naranja",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/naranja.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/naranja.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^(589562)",
          "exclusion_pattern": null,
          "installments_pattern": "^(589562)"
        },
        "card_number": {
          "length": 16,
          "validation": "none"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_identification_type",
      "cardholder_name",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "nativa",
    "name": "Nativa Mastercard",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/nativa.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/nativa.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^((520053)|(546553)|(554472)|(531847)|(527601))",
          "exclusion_pattern": null,
          "installments_pattern": "^((520053)|(546553)|(554472)|(531847)|(527601))"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_identification_number",
      "cardholder_name",
      "cardholder_identification_type"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "tarshop",
    "name": "Tarjeta Shopping",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/tarshop.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/tarshop.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^(603488)",
          "exclusion_pattern": null,
          "installments_pattern": "^(603488)"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      },
      {
        "bin": {
          "pattern": "^(27995)",
          "exclusion_pattern": null,
          "installments_pattern": "^(27995)"
        },
        "card_number": {
          "length": 13,
          "validation": "none"
        },
        "security_code": {
          "mode": "optional",
          "length": 0,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_number",
      "cardholder_identification_type"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "cencosud",
    "name": "Cencosud",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/cencosud.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/cencosud.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^(603493)",
          "exclusion_pattern": null,
          "installments_pattern": "^(603493)"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_type",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "cabal",
    "name": "Cabal",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/cabal.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/cabal.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^((627170)|(589657)|(603522)|(604((20[1-9])|(2[1-9][0-9])|(3[0-9]{2})|(400))))",
          "exclusion_pattern": null,
          "installments_pattern": "^((627170)|(589657)|(603522)|(604((20[1-9])|(2[1-9][0-9])|(3[0-9]{2})|(400))))"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_type",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "diners",
    "name": "Diners",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/diners.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/diners.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^((30)|(36)|(38))",
          "exclusion_pattern": "^((3646)|(3648))",
          "installments_pattern": "^((30)|(36)|(38))"
        },
        "card_number": {
          "length": 14,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_identification_type",
      "cardholder_name",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "argencard",
    "name": "Argencard",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/argencard.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/argencard.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^(501105)",
          "exclusion_pattern": "^((589562)|(527571)|(527572))",
          "installments_pattern": "^(501105)"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "mandatory",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_identification_type",
      "cardholder_name",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "pagofacil",
    "name": "Pago Fácil",
    "payment_type_id": "ticket",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/pagofacil.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/pagofacil.gif",
    "deferred_capture": "does_not_apply",
    "settings": [],
    "additional_info_needed": [],
    "min_allowed_amount": 2,
    "max_allowed_amount": 5000,
    "accreditation_time": 10080
  },
  {
    "id": "rapipago",
    "name": "Rapipago",
    "payment_type_id": "ticket",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/rapipago.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/rapipago.gif",
    "deferred_capture": "does_not_apply",
    "settings": [],
    "additional_info_needed": [],
    "min_allowed_amount": 0.01,
    "max_allowed_amount": 60000,
    "accreditation_time": 10080
  },
  {
    "id": "redlink",
    "name": "Red Link",
    "payment_type_id": "atm",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/redlink.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/redlink.gif",
    "deferred_capture": "does_not_apply",
    "settings": [],
    "additional_info_needed": [],
    "min_allowed_amount": 0.01,
    "max_allowed_amount": 60000,
    "accreditation_time": 10080
  },
  {
    "id": "bapropagos",
    "name": "Provincia NET",
    "payment_type_id": "ticket",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/bapropagos.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/bapropagos.gif",
    "deferred_capture": "does_not_apply",
    "settings": [],
    "additional_info_needed": [],
    "min_allowed_amount": 0.01,
    "max_allowed_amount": 60000,
    "accreditation_time": 10080
  },
  {
    "id": "cargavirtual",
    "name": "Kioscos y comercios cercanos",
    "payment_type_id": "ticket",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/cargavirtual.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/cargavirtual.gif",
    "deferred_capture": "does_not_apply",
    "settings": [],
    "additional_info_needed": [],
    "min_allowed_amount": 0.01,
    "max_allowed_amount": 5000,
    "accreditation_time": 10080
  },
  {
    "id": "cordial",
    "name": "Cordial",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/cordial.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/cordial.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^(522135|522137|527555)",
          "exclusion_pattern": null,
          "installments_pattern": "^(522135|522137|527555)"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "optional",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_type",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "cordobesa",
    "name": "Cordobesa",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/cordobesa.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/cordobesa.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^((542702)|(544764)|(550073)|(528824))",
          "exclusion_pattern": null,
          "installments_pattern": "^((542702)|(544764)|(550073))"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "optional",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_type",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  },
  {
    "id": "cmr",
    "name": "CMR",
    "payment_type_id": "credit_card",
    "status": "active",
    "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/cmr.gif",
    "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/cmr.gif",
    "deferred_capture": "supported",
    "settings": [
      {
        "bin": {
          "pattern": "^(557039)",
          "exclusion_pattern": null,
          "installments_pattern": "^(557039)"
        },
        "card_number": {
          "length": 16,
          "validation": "standard"
        },
        "security_code": {
          "mode": "optional",
          "length": 3,
          "card_location": "back"
        }
      }
    ],
    "additional_info_needed": [
      "cardholder_name",
      "cardholder_identification_type",
      "cardholder_identification_number"
    ],
    "min_allowed_amount": 0,
    "max_allowed_amount": 250000,
    "accreditation_time": 2880
  }
]
```

### Payment

[API Documentation](https://www.mercadopago.com.ar/developers/en/api-docs/custom-checkout/create-payments/)
