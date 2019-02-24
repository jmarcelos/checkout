package au.com.dius.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import au.com.dius.challenge.checkout.Checkout;
import au.com.dius.challenge.model.Product;
import au.com.dius.challenge.offer.PricingOffer;

public class DiusExamplesScenariosTest {

	// SKUs Scanned: atv, atv, atv, vga Total expected: $249.00
	@Test
	void applyDiscountBuyXAndGetXFree() {
		Checkout c = new Checkout(createPricingRule());
		Map<String, Product> products = createProducts();
		c.scan(products.get("atv"));
		c.scan(products.get("atv"));
		c.scan(products.get("atv"));
		c.scan(products.get("vga"));
		assertEquals(new BigDecimal("249.00"), c.getTotal());
	}

	// SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd Total expected: $2718.95
	@Test
	void applyDiscountBulkPriceDiscount() {
		Checkout c = new Checkout(createPricingRule());
		Map<String, Product> products = createProducts();
		c.scan(products.get("atv"));
		c.scan(products.get("ipd"));
		c.scan(products.get("ipd"));
		c.scan(products.get("atv"));
		c.scan(products.get("ipd"));
		c.scan(products.get("ipd"));
		c.scan(products.get("ipd"));
		assertEquals(new BigDecimal("2718.95"), c.getTotal());
	}

	// SKUs Scanned: mbp, vga, ipd Total expected: $1949.98
	@Test
	void applyDiscountBuyXGetYFree() {
		Checkout c = new Checkout(createPricingRule());
		Map<String, Product> products = createProducts();
		c.scan(products.get("mbp"));
		c.scan(products.get("vga"));
		c.scan(products.get("ipd"));
		assertEquals(new BigDecimal("1949.98"), c.getTotal());
	}

	private Map<String, Product> createProducts() {
		Map<String, Product> products = new LinkedHashMap<>();
		products.put("ipd", new Product("ipd", "Super iPad", new BigDecimal("549.99")));
		products.put("mbp", new Product("mbp", "MacBook Pro", new BigDecimal("1399.99")));
		products.put("atv", new Product("atv", "Apple TV", new BigDecimal("109.50")));
		products.put("vga", new Product("vga", "VGA adapter", new BigDecimal("30.00")));
		return products;
	}

	private PricingOffer createPricingRule() {
		PricingOffer po = new PricingOffer();

		// we're going to have a 3 for 2 deal on Apple TVs. For example,
		// if you buy 3 Apple TVs, you will pay the price of 2 only
		po.createBuyXGetXFree("atv", 3, 1);
		// the brand new Super iPad will have a bulk discounted applied, where
		// the price will drop to $499.99 each,
		// if someone buys more than 4
		po.createBulkedDiscountPrice("ipd", 4, new BigDecimal("50.00"));
		// we will bundle in a free VGA adapter free of charge with every
		// MacBook Pro sold
		po.createBuyXGetYFree("mbp", "vga");
		return po;
	}

}
