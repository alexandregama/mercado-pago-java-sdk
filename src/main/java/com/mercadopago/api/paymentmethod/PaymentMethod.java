package com.mercadopago.api.paymentmethod;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethod {

	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "payment_type_id")
	private PaymentType type;
	
	@XmlElement(name = "status")
	private PaymentMethodStatus status;
	
	@XmlElement(name = "secure_thumbnail")
	private String secureThumbnail;
	
	@XmlElement(name = "thumbnail")
	private String thumbnail;
	
	@XmlElement(name = "deferred_capture")
	private String deferredCapture;
	
	@XmlElement(name = "settings")
	private List<PaymentMethodSettings> settings;
	
	@XmlElement(name = "additional_info_needed")
	private List<String> additionalInfoNeeded;

	@XmlElement(name = "min_allowed_amount")
	private Integer minAllowedAmount;
	
	@XmlElement(name = "max_allowed_amount")
	private Integer maxAllowedAmount;
	
	@XmlElement(name = "accreditation_time")
	private Integer accreditationTime;
	
	public enum PaymentMethodStatus {
		ACTIVE("active"), DEACTIVE("deactive"), TEMPORALLY_DEACTIVE("temporally_deactive");
		
		private String name;
		
		PaymentMethodStatus(String name) {
			this.name = name;
		}
		
		@JsonValue
		public String getName() {
			return name;
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public PaymentType getType() {
		return type;
	}

	public PaymentMethodStatus getStatus() {
		return status;
	}

	public String getSecureThumbnail() {
		return secureThumbnail;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public String getDeferredCapture() {
		return deferredCapture;
	}

	public List<PaymentMethodSettings> getSettings() {
		return settings;
	}
	
	public List<String> getAdditionalInfoNeeded() {
		return additionalInfoNeeded;
	}
	
	public Integer getMinAllowedAmount() {
		return minAllowedAmount;
	}
	
	public Integer getMaxAllowedAmount() {
		return maxAllowedAmount;
	}

	public Integer getAccreditationTime() {
		return accreditationTime;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", id)
			.add("name", name)
			.add("type", type)
			.add("status", status)
			.add("secureThumbnail", secureThumbnail)
			.add("thumbnail", thumbnail)
			.add("additionalInfoNeeded", additionalInfoNeeded)
			.add("minAllowedAmount", minAllowedAmount)
			.add("maxAllowedAmount", maxAllowedAmount)
			.add("accreditationTime", accreditationTime)
		.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentMethod other = (PaymentMethod) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
