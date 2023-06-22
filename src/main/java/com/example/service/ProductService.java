package com.example.service;
import com.example.Colors;
import com.example.model.Product;
import com.example.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    private List<Product> shoppingCart;

    public ProductService() {
        shoppingCart = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        shoppingCart.add(product);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : shoppingCart) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void processProduct(ShoppingCart shoppingCart) {
        System.out.print(Colors.YELLOW + "Czy chcesz potwierdzić zakup? " + Colors.RESET + "(" + Colors.GREEN + "T" + Colors.RESET + "/" + Colors.RED + "N" + Colors.RESET + "): " + Colors.RESET);

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("T")) {
            System.out.println(Colors.GREEN + "Zakup został zaakceptowany." + Colors.RESET);
            shoppingCart.clearCart();
        } else {
            System.out.println(Colors.RED + "Zakup został anulowany." + Colors.GREEN);
        }
    }
}
