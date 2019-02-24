package au.com.dius.challenge.offer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import au.com.dius.challenge.checkout.CheckoutItem;
import au.com.dius.challenge.model.Product;
import au.com.dius.challenge.offer.BulkedDiscountPrice;

public class BulkedDiscountPriceTest {
	

	@Test
	void createBulkedDiscountPriceInvalidSku() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BulkedDiscountPrice(null, 1, BigDecimal.ONE);
		});
		assertEquals("Sku, minimun quantity and discount must be supplied", exception.getMessage());
	}

	@Test
	void createBulkedDiscountPriceInvalidQuantity() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BulkedDiscountPrice("Ipd", null, BigDecimal.ONE);
		});
		assertEquals("Sku, minimun quantity and discount must be supplied", exception.getMessage());
	}

	@Test
	void createBulkedDiscountPriceInvalidDiscount() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new BulkedDiscountPrice("Ipd", 3, null);
		});
		assertEquals("Sku, minimun quantity and discount must be supplied", exception.getMessage());
	}

	@Test
	void applyDiscountQuantityBiggerThanMinimum() {
		Product p = createProduct();
		Map<String, CheckoutItem> chi = createCheckouItems(p);

		BulkedDiscountPrice bdp = new BulkedDiscountPrice(p.getSku(), 2, BigDecimal.ONE);
		bdp.applyOffer(chi);
		assertEquals(new BigDecimal("18"), chi.get(p.getSku()).getTotal());
	}
	
	@Test
	void applyDiscountQuantitySmallerThanMinimum() {
		Product p = createProduct();
		Map<String, CheckoutItem> chi = createCheckouItems(p);

		BulkedDiscountPrice bdp = new BulkedDiscountPrice(p.getSku(), 3, BigDecimal.ONE);
		bdp.applyOffer(chi);
		assertEquals(new BigDecimal("20"), chi.get(p.getSku()).getTotal());
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


