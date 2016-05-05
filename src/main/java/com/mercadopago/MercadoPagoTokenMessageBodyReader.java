package com.mercadopago;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class MercadoPagoTokenMessageBodyReader implements MessageBodyReader<MercadoPagoToken> {

	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == MercadoPagoToken.class;
	}

	public MercadoPagoToken readFrom(Class<MercadoPagoToken> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {
		try {
			JAXBContext context = JAXBContext.newInstance(MercadoPagoToken.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			MercadoPagoToken token = (MercadoPagoToken) unmarshaller.unmarshal(entityStream);
			return token;
		} catch (JAXBException e) {
			throw new ProcessingException("Error while processing MercadoPagoToken message using JAX-B");
		}
	}

}
