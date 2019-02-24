package au.com.dius.challenge.offer;

import java.util.Map;

import au.com.dius.challenge.checkout.CheckoutItem;

public interface Offer {
	public void applyOffer(Map<String, CheckoutItem> items);
}
