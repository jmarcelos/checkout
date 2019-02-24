package au.com.dius.challenge.checkout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import au.com.dius.challenge.model.Product;

public class CheckoutTest {

	@Test
	void scanInvalidItem() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			Checkout c = new Checkout(null);
			c.scan(null);
		});
		assertEquals("A valid product is mandatory", exception.getMessage());
	}

	@Test
	void scanOneItem() {
		Product p = new Product("A1", "AAAA", new BigDecimal("1.00"));
		Checkout c = new Checkout(null);
		c.scan(p);
		assertEquals(p.getPrice(), c.getTotal());
	}

	@Test
	void scanTwoUniqueItems() {
		Product p1 = new Product("A1", "AAAA", new BigDecimal("1.00"));
		Product p2 = new Product("A2", "AAAA", new BigDecimal("1.20"));
		Checkout c = new Checkout(null);
		c.scan(p1);
		c.scan(p2);
		assertEquals(p1.getPrice().add(p2.getPrice()), c.getTotal());
	}

	@Test
	void scanTwoSimilarItems() {
		Product p = new Product("A1", "AAAA", new BigDecimal("1.00"));
		Checkout c = new Checkout(null);
		c.scan(p);
		c.scan(p);
		assertEquals(p.getPrice().multiply(new BigDecimal(2)), c.getTotal());
	}
}
