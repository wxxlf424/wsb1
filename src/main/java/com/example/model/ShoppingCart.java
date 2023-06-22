package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Colors;

/**
 * Klasa ShoppingCart reprezentuje koszyk zakupowy, 
 * który przechowuje produkty wraz z ich ilością. 
 * Klasa udostępnia metody do dodawania produktów do koszyka, 
 * pobierania listy produktów, pobierania ilości danego produktu, 
 * obliczania całkowitej wartości produktów w koszyku oraz czyszczenia koszyka.
 */
public class ShoppingCart {
    private Map<Product, Integer> products;

    /**
     * Tworzy nowy obiekt typu ShoppingCart.
     */
    public ShoppingCart() {
        this.products = new HashMap<>();
    }
    
    /**
     * Dodaje produkt do koszyka.
     *
     * @param product produkt do dodania
     */
    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    /**
     * Zwraca listę produktów w koszyku.
     *
     * @return lista produktów w koszyku
     */
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

    /**
     * Zwraca ilość danego produktu w koszyku.
     *
     * @param product produkt, którego ilość ma być zwrócona
     * @return ilość produktu w koszyku
     */
    public int getProductQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    /**
     * Oblicza całkowitą wartość produktów w koszyku.
     *
     * @return całkowita wartość produktów w koszyku
     */
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    /**
     * Czyści koszyk.
     */
    public void clearCart() {
        products.clear();
        System.out.println(Colors.GREEN + "Koszyk został wyczyszczony!" + Colors.RESET);
    }
}
