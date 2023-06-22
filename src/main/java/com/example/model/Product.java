package com.example.model;

/**
 * Klasa Product reprezentuje produkt, który ma nazwę, cenę i ilość. 
 * Domyślnie, ilość jest ustawiona na 1. 
 * Klasa dostarcza metody dostępowe do pobierania nazwy, 
 * ceny i ilości produktu, a także metody ustawiającej ilość 
 * oraz obliczającej całkowitą wartość produktu (cena * ilość).
 */
public class Product {
    private String name;
    private double price;
    private int quantity;

    /**
     * Tworzy nowy obiekt typu Product.
     *
     * @param name   nazwa produktu
     * @param price  cena produktu
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 1; // Domyślna wartość ilości to 1
    }

    /**
     * Zwraca nazwę produktu.
     *
     * @return nazwa produktu
     */
    public String getName() {
        return name;
    }

    /**
     * Zwraca cenę produktu.
     *
     * @return cena produktu
     */
    public double getPrice() {
        return price;
    }

    /**
     * Zwraca ilość produktu.
     *
     * @return ilość produktu
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Ustawia ilość produktu.
     *
     * @param quantity ilość produktu
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Oblicza całkowitą wartość produktu (cena * ilość).
     *
     * @return całkowita wartość produktu
     */
    public double getTotalPrice() {
        return price * quantity;
    }
}
