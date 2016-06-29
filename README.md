# Mercado Pago Java SDK


[![Stories in Ready](https://badge.waffle.io/alexandregama/mercado-pago-java-sdk.png?label=ready&title=Ready)](http://waffle.io/alexandregama/mercado-pago-java-sdk)

[![Throughput Graph](https://graphs.waffle.io/alexandregama/mercado-pago-java-sdk/throughput.svg)](https://waffle.io/alexandregama/mercado-pago-java-sdk/metrics/throughput)

## Authenticating your application

It's pretty simple authenticate your application to be able to use Mercado Pago Api. Just follow the code bellow:

```java
TokenCredentials credentials = new TokenCredentials("your_client_id", "your_secret_key");
```

But where can I get my credentials? It's pretty easy also! Go to the [credentials page](https://www.mercadopago.com/mla/account/credentials?type=basic) on Mercado Pago and get it :)

After that you can use the **MercadoPagoApi** class that contains all operations you need. The following code works well

```java
MercadoPagoCredentials credentials = new MercadoPagoCredentials("your_client_id", "your_secret_key");

MercadoPagoApi mercadoPagoApi = new MercadoPagoJerseyClient(credentials);
```

Notice that we are using Jersey as implementation. All SDK code is based on Interfaces and you can implement your own code \o/. We are planning to implement the SDK with [Java native URL](https://docs.oracle.com/javase/7/docs/api/java/net/URL.html). 

Feel free to send your [Pull Request](https://help.github.com/articles/using-pull-requests/) with your own implementation! :)

With **mercadoPagoApi** in your hands, you are capable to retrieve a few api implementations as follows:

```java
public interface MercadoPagoApi {

	PaymentMethodApi paymentMethods();
	
	PreferenceApi preferences();
	
	PaymentApi payments();

}
```

Every method on MercadoPagoApi retrieves a Api implementation. Bellow you will see how to use them :)

## Preference

**Understanding Preference process**

You should use a Preference creation when you are working with **Basic Checkout**. Don't worry, it's easy :)

Let's getting started!

First of all, there are 3 major actions that you can use, according with [api documentation](https://www.mercadopago.com.ar/developers/en/api-docs/basic-checkout/checkout-preferences/):

- Retrieve preference data

- Create preference

- Modify preference data

This API allows you to set up, during the payment process on Basic Checkout, all the item information, any accepted means of payment and a lot of other options as:

- Items informations

- Buyer informations

- Payment methods accepteds for payment process

- Shipment information

- A lot of readable attributes

A preference needs to be created when you would like to use basic checkout, meaning that you'll send your customer/buyer to Mercado Pago's site usig a special **url**.

For example, your customer purchased something in your site and need to pay for that. At this moment, you can **Create a new Preference** based on your customers order, as **Items** informations, **Buyer** informations, **Shipments** characteristics and so on and you can create a link to send your buyer to Mercado Pago's site.

**Creating a new Preference**

To create a new Preference you just need to create a new **Preference** object with all informations that you need

In the following example you will see how to create a new Preference with basic informations:

```java
Preference preference = new Preference();
Item item = Item
	.fromId("1")
	.withProductNamed("First Product")
	.withDescription("First Awesome Product")
	.costing(TEN)
	.withQuantity(10)
	.usingPictureOnUrl("http://s3.amazon.com/mercadopago/image.png")
	.fromCategory("Music")
	.withCurrecyCode("ARS")
	.build();
preference.addItem(item);
```

Notice that you must use the **Item** class to describe your Item informations. If you prefer, you could use Item class without the builder, just using all setters methods on Item class.

You can describe your Buyer also just using the following code:

```java
PreferencePayer payer = new PreferencePayer();
payer.setName("Alexandre");
payer.setLastname("Gama");
payer.setEmail("alexandre.gama.lima@gmail.com");
payer.setPhone(new Phone("55", "987653786"));
preference.setPayer(payer);
```

What about Buyer address? It's easy too!

```java
Address address = new Address();
address.setZipCode("04676500");
address.setStreetNumber(40);
address.setStreetName("First Street");
payer.setAddress(address);
```

Now its the expected time to create a new Preference \o/. Let's use the code that we created until now!

```java
MercadoPagoCredentials credentials = new MercadoPagoCredentials("your_client_id", "your_secret_key");

MercadoPagoApi mercadoPagoApi = new MercadoPagoJerseyClient(credentials);

Preference preferenceCreted = mercadoPagoApi.preferences().createNew(preference);
```

Simple as that man \o/. And we will return to you the Payment created :)

## Payment Methods

Payment Methods are used on Custom Checkout. It just mean all payments that are available to be used.

The only action to be made is **get payment methods**

To do that, you can follow the code bellow:

```java
```java
MercadoPagoCredentials credentials = new MercadoPagoCredentials("your_client_id", "your_secret_key");

MercadoPagoApi mercadoPagoApi = new MercadoPagoJerseyClient(credentials);

Preference preferenceCreted = mercadoPagoApi.preferences().createNew(preference);

```java
MercadoPagoCredentials credentials = new MercadoPagoCredentials("your_client_id", "your_secret_key");

MercadoPagoApi mercadoPagoApi = new MercadoPagoJerseyClient(credentials);

List<PaymentMethod> paymentAcceptedMethods = mercadoPagoApi.paymentMethods().getAll();
```

Simple as that again! \o/

## Payment

This service allows you to create, read or update a new Payment on Custom Checkout.

You can do the following actions:

- Retrieves information about a payment

- Updates a payment

- Issues a new payment

**Creating a new Payment**

To create a new Payment you just need to create a **Payment object** and fill all informations that you need.

Filling buyer informations

```java
PaymentPayer payer = new PaymentPayer();
payer.setCustomerId("218136417-Npn1qbvt94mMJ2");
payer.setEmail("alexandre.gama@elo7.com");
```

What about Payer's address? 

```java
Address address = new Address();
address.setStreetName("Rua Beira Rio");
address.setStreetNumber(70);
address.setZipCode("04689115");
```

Now its the moment to create a new Payment object using all informations above :)

```java
PaymentToCreate payment = new PaymentToCreate();
payment.setDescription("Title of what you are paying for");
payment.setTransactionAmount(BigDecimal.TEN);
payment.setPaymentMethodId(paymentMethod.getId());
payment.setInstallments(12);
payment.setPayer(payer);
```

Last, you must pass the Payment object to createNew method on payments api as follows:

```java
MercadoPagoCredentials credentials = new MercadoPagoCredentials("your_client_id", "your_secret_key");

MercadoPagoApi mercadoPagoApi = new MercadoPagoJerseyClient(credentials);

PaymentRetrieved paymentCreated = mercadoPagoApi.payments().createNew(payment);
```

You must notice that we are using different objects to deal with Payment, the **&PaymentToCreate** object and the **PaymentRetrieved** object. This is necessary once Mercado Pago deal with them differently, meaning that the Payment that has been created does not match with the Payment created internaly by Mercado Pago.

# Mercado Pago REST API

## Payment Methods

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

### Mercado Pago Connect

To edit the Redirect URL you must go to the [api documentation page](https://applications.mercadopago.com.br/show?appId=8745648399028232&platform=mp)


# Mercado Pago Documentation

Here you'll find a few documentations about the Mercado Pago usage

### Basic Authentication

[Oficial Api Documentation](https://www.mercadopago.com.ar/developers/en/api-docs/basics/authentication/)

**Authentication**

Using your credentials, you can be sure that your data is only available for your application.

You must follow the steps:

1 - Send your **credentials** and receive your **access_token**.

2 - Use your **access_token** to operate with the API.

**How to obtain my access_token?**

First, you'll need to obtain your [credentials](https://www.mercadopago.com/mlb/account/credentials?type=basic). In that link you'll see that you can get credentials for **Basic Checkout** and **Custom Checkout**

Then, you must send to Mercado Pago your **CLIENT_ID** and **CLIENT_SECRET** using the Mercado Pago Java SDK.

Just to understand what is underhood, you can see the **curl** command bellow with the necessary code

```bash
curl -X POST \
     -H 'accept: application/json' \
     -H 'content-type: application/x-www-form-urlencoded' \
     'https://api.mercadopago.com/oauth/token' \
     -d 'grant_type=client_credentials' \
     -d 'client_id=CLIENT_ID' \
     -d 'client_secret=CLIENT_SECRET'
```

Notice that you must use it for every operation you do with the API, sending it as a parameter in the URL: ```https://api.mercadopago.com/.../?access_token=YOUR_ACCESS_TOKEN```


### Custom Checkout

Using your credentials, you can be sure that your data is only available for your application.

**Authentication**

Each application has two pairs of keys for connecting with the MercadoPago API. You can see it at [oficial documentation](https://www.mercadopago.com/mlb/account/credentials)

The sandbox environment keys allows you to make **test payments**, and the production ones, **real payments**.

Besides the sandbox and production environments, one of the keys is **public** (PUBLIC_KEY) and the other is **private** (ACCESS_TOKEN).

The public keys identifies your MercadoPago account and aren't secret.

These keys are used in frontends, through the MercadoPago SDKs, in mobile applications or in Javascript code.

For example, you can use your **public key** to retrieve a installments resouce using GET request on Mercado Pago Api

```bash
curl -G -X GET \
	-H "accept: application/json" \
	"https://api.mercadopago.com/v1/payment_methods/installments" \
	-d "public_key=PUBLIC_KEY" \
	-d "amount=100" \
	-d "payment_method_id=visa"
```

The private keys must be kept confidentially in your backend servers and never should be published.

Example of a GET request to the payments resource using your private key (ACCESS_TOKEN):

```bash
curl -G -X GET \
	-H "accept: application/json" \
	"https://api.mercadopago.com/v1/payments/[ID]" \
	-d "access_token=ACCESS_TOKEN"
```
