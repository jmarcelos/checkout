package au.com.dius.challenge.offer;

import java.math.BigDecimal;
import java.util.Map;

import au.com.dius.challenge.checkout.CheckoutItem;

public class BulkedDiscountPrice implements Offer {

	private final String sku;
	private final Integer minQuantity;
	private final BigDecimal discount;

	public BulkedDiscountPrice(String sku, Integer minQuantity, BigDecimal discount) {

		if (sku == null || minQuantity == null || discount == null) {
			throw new IllegalArgumentException("Sku, minimun quantity and discount must be supplied");
		}

		this.sku = sku;
		this.minQuantity = minQuantity;
		this.discount = discount;
	}

	@Override
	public void applyOffer(Map<String, CheckoutItem> items) {

		CheckoutItem ci = items.get(sku);
		if (ci != null) {
			BigDecimal discount = calculateDiscount(ci.getQuantity());
			ci.setDiscount(discount);
		}
	}

	private BigDecimal calculateDiscount(Integer quantity) {

		BigDecimal totalDiscount = BigDecimal.ZERO;
		if (quantity >= minQuantity) {
			totalDiscount = discount.multiply(BigDecimal.valueOf(quantity));
		}
		return totalDiscount;
	}

}
