package au.com.dius.challenge.offer;

import java.math.BigDecimal;
import java.util.Map;

import au.com.dius.challenge.checkout.CheckoutItem;


public class BuyXGetYFree implements Offer {

	private final String sku;
	private final String freeSku;

	public BuyXGetYFree(String sku, String freeSku) {

		if (sku == null || freeSku == null) {
			throw new IllegalArgumentException("Sku Offer and SKU free must be supplied");
		}

		this.sku = sku;
		this.freeSku = freeSku;
	}

	@Override
	public void applyOffer(Map<String, CheckoutItem> items) {

		if (items.containsKey(sku) && items.containsKey(freeSku)) {
			CheckoutItem ci = items.get(sku);
			CheckoutItem ciFree = items.get(freeSku);
			Integer quantityFree = Math.min(ci.getQuantity(), ciFree.getQuantity());
			ciFree.setDiscount(ciFree.getUnitPrice().multiply(BigDecimal.valueOf(quantityFree)));
		}
	}

}
