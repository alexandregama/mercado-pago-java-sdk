package com.mercadopago.api.payment;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class PaymentItem {

	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "title")
	private String title;
	
	@XmlElement(name = "description")
	private String description;
	
	@XmlElement(name = "picture_url")
	private String pictureUrl;
	
	@XmlElement(name = "category_id")
	private String category;
	
	@XmlElement(name = "quantity", required = true)
	private int quantity;
	
	@XmlElement(name = "unit_price", required = true)
	private BigDecimal price;
	
	public PaymentItem(String id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public PaymentItem() {}
	
	public static ItemBuilder fromId(String id) {
		return new ItemBuilder(id);
	}
	
	public static class ItemBuilder {
		
		private PaymentItem item;
		
		public ItemBuilder(String id) {
			item = new PaymentItem();
			item.setId(id);
		}

		public ItemBuilder withProductNamed(String productName) {
			item.setTitle(productName);
			return this;
		}

		public ItemBuilder withDescription(String description) {
			item.setDescription(description);
			return this;
		}
		
		public ItemBuilder withTitle(String title) {
			item.setTitle(title);
			return this;
		}

		public ItemBuilder costing(BigDecimal price) {
			item.setPrice(price);
			return this;
		}

		public ItemBuilder withQuantity(int quantity) {
			item.setQuantity(quantity);
			return this;
		}

		public ItemBuilder usingPictureOnUrl(String pictureUrl) {
			item.setPictureUrl(pictureUrl);
			return this;
		}

		public ItemBuilder fromCategory(String category) {
			item.setCategory(category);
			return this;
		}

		public PaymentItem build() {
			return this.item;
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "PaymentItem [id=" + id + ", title=" + title + ", description=" + description + ", pictureUrl="
				+ pictureUrl + ", category=" + category + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
