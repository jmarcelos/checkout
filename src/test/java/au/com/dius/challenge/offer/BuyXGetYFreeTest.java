package au.com.dius.challenge.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import au.com.dius.challenge.checkout.CheckoutItem;
import au.com.dius.challenge.model.Product;
import au.com.dius.challenge.offer.BuyXGetYFree;

public class BuyXGetYFreeTest {

	@Test
	void createBuyXGetXFreePriceInvalidSku() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BuyXGetYFree(null, "IPD");
		});
		assertEquals("Sku Offer and SKU free must be supplied", exception.getMessage());
	}

	@Test
	void createBuyXGetXFreeInvalidQuantity() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BuyXGetYFree("Ipd", null);
		});
		assertEquals("Sku Offer and SKU free must be supplied", exception.getMessage());
	}

	@Test
	void applyDiscountQuantityFreeAndOfferEqual() {
		Product pOffer = new Product("mbk", "Mackbook", BigDecimal.TEN);
		Product pFree = new Product("vda", "adaptor", BigDecimal.ONE);
 		Map<String, CheckoutItem> chi = createCheckouItems(pOffer, pFree);

		BuyXGetYFree bgf = new BuyXGetYFree(pOffer.getSku(), pFree.getSku());
		bgf.applyOffer(chi);
		assertEquals(BigDecimal.ZERO, chi.get(pFree.getSku()).getTotal());
	}
	
	@Test
	void applyDiscountQuantityFreeSmallerThanOffer() {
		Product pOffer = new Product("mbk", "Mackbook", BigDecimal.TEN);
		Product pFree = new Product("vda", "adaptor", BigDecimal.ONE);
 		Map<String, CheckoutItem> chi = createCheckouItems(pOffer, pFree);
 		chi.get(pOffer.getSku()).increaseQuantity();

		BuyXGetYFree bgf = new BuyXGetYFree(pOffer.getSku(), pFree.getSku());
		bgf.applyOffer(chi);
		assertEquals(BigDecimal.ZERO, chi.get(pFree.getSku()).getTotal());
	}
	
	@Test
	void applyDiscountQuantityFreeBiggerThanOffer() {
		Product pOffer = new Product("mbk", "Mackbook", BigDecimal.TEN);
		Product pFree = new Product("vda", "adaptor", BigDecimal.ONE);
 		Map<String, CheckoutItem> chi = createCheckouItems(pOffer, pFree);
 		chi.get(pFree.getSku()).increaseQuantity();

		BuyXGetYFree bgf = new BuyXGetYFree(pOffer.getSku(), pFree.getSku());
		bgf.applyOffer(chi);
		assertEquals(BigDecimal.ONE, chi.get(pFree.getSku()).getTotal());
	}


	private Map<String, CheckoutItem> createCheckouItems(Product pOffer, Product pFree) {
		Map<String, CheckoutItem> chi = new LinkedHashMap<>();
		CheckoutItem ci = new CheckoutItem(pFree);
		ci.increaseQuantity();
		chi.put(ci.getSKU(), ci);
		ci = new CheckoutItem(pOffer);
		ci.increaseQuantity();
		chi.put(ci.getSKU(), ci);
		return chi;
	}

}
