package au.com.dius.challenge.offer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import au.com.dius.challenge.checkout.CheckoutItem;

public class PricingOffer {

	private Collection<Offer> offers = new ArrayList<>();
	
	public void applyOffers(Map<String, CheckoutItem> items) {
		offers.stream().forEach(o-> o.applyOffer(items));
	}
	
	public void createBuyXGetXFree(String skuOffer, Integer minBuyingQuantity, Integer freeQuantity) {
		offers.add(new BuyXGetXFree(skuOffer, minBuyingQuantity, freeQuantity)); 
	}
	
	public void createBuyXGetYFree(String skuOffer, String skuFree) {
		offers.add(new BuyXGetYFree(skuOffer, skuFree)); 
	}
	
	public void createBulkedDiscountPrice(String skuOffer, Integer minBuyingQuantity, BigDecimal discount) {
		offers.add(new BulkedDiscountPrice(skuOffer, minBuyingQuantity, discount)); 
	}
}
