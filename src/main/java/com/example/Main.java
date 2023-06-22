package com.example;

import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.example.service.ProductService;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Product 1", 9.99);
        Product product2 = new Product("Product 2", 19.99);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(product1);
        shoppingCart.addProduct(product2);

        ProductService productService = new ProductService();
        for (Product product : shoppingCart.getProducts()) {
            productService.processProduct(product);
        }
    }
}