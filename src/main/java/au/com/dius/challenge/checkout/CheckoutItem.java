package au.com.dius.challenge.checkout;

import java.math.BigDecimal;

import au.com.dius.challenge.model.Product;

public class CheckoutItem {

	private final String sku;
	private final BigDecimal unitPrice;
	private Integer quantity = 1;
	private BigDecimal discount = BigDecimal.ZERO;

	public CheckoutItem(Product item) {
		this.sku = item.getSku();
		this.unitPrice = item.getPrice();
	}

	public CheckoutItem increaseQuantity() {
		++quantity;
		return this;
	}

	public CheckoutItem decreaseQuantity() {
		--quantity;
		return this;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public BigDecimal getTotal() {
		return unitPrice.multiply(BigDecimal.valueOf(quantity)).subtract(discount);
	}

	public String getSKU() {
		return sku;
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
		CheckoutItem other = (CheckoutItem) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
}
