package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> products;

    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            int quantity = products.get(product);
            if (quantity > 1) {
                products.put(product, quantity - 1);
            } else {
                products.remove(product);
            }
        }
    }
    
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                productList.add(product);
            }
        }
        return productList;
    }

    public int getProductQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void clearCart() {
        products.clear();
    }
}
