package au.com.dius.challenge.checkout;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import au.com.dius.challenge.model.Product;
import au.com.dius.challenge.offer.PricingOffer;

public class Checkout {

	private Map<String, CheckoutItem> checkoutItems = new LinkedHashMap<>();
	private final PricingOffer pricingOffer;

	public Checkout(PricingOffer pricingOffer) {
		this.pricingOffer = pricingOffer;
	}

	/*
	 * NOT THREAD SAFE
	 */
	public void scan(Product item) {

		if (item == null) {
			throw new IllegalArgumentException("A valid product is mandatory");
		}

		CheckoutItem ci = new CheckoutItem(item);
		if (checkoutItems.containsKey(item.getSku())) {
			ci = checkoutItems.get(item.getSku());
			ci.increaseQuantity();
		}
		checkoutItems.put(item.getSku(), ci);

	}

	private void applyDiscounts() {
		if (pricingOffer != null) {
			pricingOffer.applyOffers(checkoutItems);
		}
	}

	public BigDecimal getTotal() {
		applyDiscounts();
		return checkoutItems.values().parallelStream().map(ci -> ci.getTotal()).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}

}
