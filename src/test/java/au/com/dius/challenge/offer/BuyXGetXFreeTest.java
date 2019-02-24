package au.com.dius.challenge.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import au.com.dius.challenge.checkout.CheckoutItem;
import au.com.dius.challenge.model.Product;
import au.com.dius.challenge.offer.BuyXGetXFree;

public class BuyXGetXFreeTest {

	@Test
	void createBuyXGetXFreePriceInvalidSku() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BuyXGetXFree(null, 3, 1);
		});
		assertEquals("Sku, buying quantity and free quantity must be supplied", exception.getMessage());
	}

	@Test
	void createBuyXGetXFreeInvalidQuantity() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BuyXGetXFree("Ipd", null, 1);
		});
		assertEquals("Sku, buying quantity and free quantity must be supplied", exception.getMessage());
	}

	@Test
	void createBuyXGetXFreePriceInvalidDiscount() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BuyXGetXFree("Ipd", 3, null);
		});
		assertEquals("Sku, buying quantity and free quantity must be supplied", exception.getMessage());
	}

	@Test
	void applyDiscountQuantityBiggerThanMinimum() {
		Product p = createProduct();
		Map<String, CheckoutItem> chi = createCheckouItems(p);

		BuyXGetXFree bgf = new BuyXGetXFree(p.getSku(), 2, 1);
		bgf.applyOffer(chi);
		assertEquals(new BigDecimal("10"), chi.get(p.getSku()).getTotal());
	}
	
	@Test
	void applyDiscountQuantitySmallerThanMinimum() {
		Product p = createProduct();
		Map<String, CheckoutItem> chi = createCheckouItems(p);

		BuyXGetXFree bgf = new BuyXGetXFree(p.getSku(), 4, 2);
		bgf.applyOffer(chi);
		assertEquals(new BigDecimal("20"), chi.get(p.getSku()).getTotal());
	}
	
	@Test
	void applyDiscountQuantityEqualThanMinimum() {
		Product p = createProduct();
		Map<String, CheckoutItem> chi = createCheckouItems(p);

		BuyXGetXFree bgf = new BuyXGetXFree(p.getSku(), 2, 2);
		bgf.applyOffer(chi);
		assertEquals(new BigDecimal("0"), chi.get(p.getSku()).getTotal());
	}
	
	
	
	private Product createProduct() {
		return new Product("Q1", "IPad", BigDecimal.TEN);
	}
	
	private Map<String, CheckoutItem> createCheckouItems(Product p) {
		Map<String, CheckoutItem> chi = new LinkedHashMap<>();
		CheckoutItem ci = new CheckoutItem(p);
		ci.increaseQuantity();
		chi.put(ci.getSKU(), ci);
		return chi;
	}

	
}
