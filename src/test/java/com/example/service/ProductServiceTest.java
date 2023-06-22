package com.example.service;

import com.example.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {
    @Test
    public void testProcessProduct() {
        Product product = new Product("Test Product", 9.99);

        ProductService productService = new ProductService();
        productService.processProduct(product);

        // Sprawdzenie oczekiwanego zachowania
        assertEquals(9.99, product.getPrice());
    }
}