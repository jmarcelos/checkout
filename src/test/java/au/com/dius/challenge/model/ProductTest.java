package au.com.dius.challenge.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import au.com.dius.challenge.model.Product;

public class ProductTest {

	@Test
	void createProductInValidSKU() {
				
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {new Product(null, "Yong", new BigDecimal("12.01"));});
        assertEquals("Sku price and name are mandatory", exception.getMessage());
	}

	@Test
	void createProductInValidName() {
				
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {new Product("A1", null, new BigDecimal("12.01"));});
        assertEquals("Sku price and name are mandatory", exception.getMessage());
	}
	
	@Test
	void createProductInValidPrice() {
				
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {new Product("A1", "Yong", null);});
        assertEquals("Sku price and name are mandatory", exception.getMessage());
	}
	
	@Test
	void createWithSKUNameAndPrice() {
		String name = "IPad";
		String sku = "Q1";
		BigDecimal price = new BigDecimal("12.01");
		Product p = new Product(sku, name, price);
		assertEquals(p.getPrice(), price);
		assertEquals(p.getSku(), sku);
		assertEquals(p.getName(), name);
	}
	
}
