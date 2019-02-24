package au.com.dius.challenge.offer;

import java.math.BigDecimal;
import java.util.Map;

import au.com.dius.challenge.checkout.CheckoutItem;

public class BuyXGetXFree implements Offer{

	private final String sku;
	private final Integer freeQuantity;
	private final Integer buyQuantity;
	
	public BuyXGetXFree(String sku, Integer buyQuantity, Integer freeQuantity) {
		
		if (sku == null || buyQuantity == null || freeQuantity == null) {
			throw new IllegalArgumentException("Sku, buying quantity and free quantity must be supplied");
		}
		
		this.sku = sku;
		this.freeQuantity = freeQuantity;
		this.buyQuantity = buyQuantity;
	}

	@Override
	public void applyOffer(Map<String, CheckoutItem> items) {
		
		CheckoutItem ci = items.get(sku);
		if (ci != null) {
			BigDecimal discount = calculateDiscount(ci.getUnitPrice(), ci.getQuantity());
			ci.setDiscount(discount);
		}
	}
	
	private BigDecimal calculateDiscount(BigDecimal price, Integer quantity) {
	
		if (price == null || quantity == null) {
			throw new IllegalArgumentException("Price and Quantity must be supplied");
		}

		try {			
			Integer countApplyOffer = quantity / buyQuantity;
			Integer totalFreeItems = countApplyOffer * freeQuantity;
			BigDecimal discount = price.multiply(BigDecimal.valueOf(totalFreeItems));
			return discount;
		} catch (ArithmeticException e) {
			return BigDecimal.ZERO;
		}
		
	}

}
