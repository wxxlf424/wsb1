package com.example.service;

import com.example.Colors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.model.Product;
import com.example.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa ProductService jest klasą serwisową, która obsługuje operacje na produktach. 
 * Udostępnia metody do dodawania produktów do koszyka, 
 * obliczania całkowitej wartości produktów w koszyku oraz przetwarzania 
 * produktów w koszyku poprzez potwierdzenie lub anulowanie zakupu.
 */

public class ProductService {
    private List<Product> shoppingCart;

    /**
     * Tworzy nowy obiekt typu ProductService.
     */
    public ProductService() {
        shoppingCart = new ArrayList<>();
    }

    /**
     * Dodaje produkt do koszyka.
     *
     * @param product produkt do dodania
     */
    public void addProductToCart(Product product) {
        shoppingCart.add(product);
    }

    /**
     * Oblicza całkowitą wartość produktów w koszyku.
     *
     * @return całkowita wartość produktów w koszyku
     */
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : shoppingCart) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    /**
     * Przetwarza produkty w koszyku.
     *
     * @param shoppingCart koszyk zakupowy
     */
    public boolean processProduct(ShoppingCart shoppingCart) {
        System.out.print(Colors.YELLOW + "Czy chcesz potwierdzić zakup? " + Colors.RESET + "(" + Colors.GREEN + "T" + Colors.RESET + "/" + Colors.RED + "N" + Colors.RESET + "): " + Colors.RESET);

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("T")) {
            System.out.println(Colors.GREEN + "Zakup został zaakceptowany." + Colors.RESET);
            shoppingCart.clearCart();
            return true; // Zatwierdzenie koszyka
        } else {
            System.out.println(Colors.RED + "Zakup został odrzucony." + Colors.GREEN);
            return false; // Odrzucenie koszyka
        }
    }
}
