package com.example.service;
import com.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> shoppingCart;

    public ProductService() {
        shoppingCart = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        shoppingCart.add(product);
    }

    public void calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : shoppingCart) {
            totalPrice += product.getPrice();
        }
        System.out.println("Kwota końcowa: " + totalPrice);
    }

    public void processProduct(Product product) {
        System.out.println("Czy chcesz potwierdzić zakup? (T/N)");
        // Tutaj możesz dodać kod do pobrania odpowiedzi od użytkownika

        // Przykładowa logika potwierdzania zakupu
        String response = "T";
        if (response.equalsIgnoreCase("T")) {
            System.out.println("Zakup został zaakceptowany.");
            shoppingCart.clear();
        } else {
            System.out.println("Zakup został anulowany.");
        }
    }
}
