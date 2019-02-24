package au.com.dius.challenge.model;

import java.math.BigDecimal;

public class Product {

	private final String sku;
	private final String name;
	private final BigDecimal price;
	
	/*
	 * If there was more parameters we should consider using Builder Pattern
	 * */
	public Product(String sku, String name, BigDecimal price) {
		
		if (sku == null || name == null || price == null) {
			throw new IllegalArgumentException("Sku price and name are mandatory");
		}
		this.sku = sku;
		this.name = name;
		this.price = price;			
	}


	public String getSku() {
		return sku;
	}


	public String getName() {
		return name;
	}


	public BigDecimal getPrice() {
		return price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Product other = (Product) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
	
}
