package com.example;

import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.example.service.ProductService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * Główna klasa aplikacji.
 */
public class Main {
    /**
     * Metoda główna aplikacji.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);

        List<Product> availableProducts = readProductsFromFile("products.txt");

        while (true) {
            System.out.println(Colors.GREEN + "======= Menu =======" + Colors.RESET);
            System.out.println("1. Wyświetl dostępne produkty");
            System.out.println("2. Dodaj produkt do koszyka");
            System.out.println("3. Podgląd koszyka");
            System.out.println("4. Wyczyść koszyk");
            System.out.println("5. Zrealizuj produkty w koszyku");
            System.out.println(Colors.RED + "6. Zakończ" + Colors.RESET);

            System.out.print(Colors.YELLOW + "Wybierz opcję: " + Colors.RESET);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    displayAvailableProducts(availableProducts);
                    break;
                case 2:
                    addProductToCart(availableProducts, shoppingCart, scanner);
                    break;
                case 3:
                    displayShoppingCart(shoppingCart);
                    break;
                case 4:
                    shoppingCart.clearCart();
                    break;
                case 5:
                    processProducts(shoppingCart, productService);
                    break;
                case 6:
                    System.out.println("Żegnaj!");
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                    break;
            }

            System.out.println();
        }
    }

    /**
     * Wczytuje produkty z pliku.
     *
     * @param fileName nazwa pliku
     * @return lista wczytanych produktów
     */
    private static List<Product> readProductsFromFile(String fileName) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 2) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);

                    Product product = new Product(name, price);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println(Colors.RED + "Błąd odczytu pliku: " + e.getMessage() + Colors.RESET);
        }

        return products;
    }

    /**
     * Wyświetla dostępne produkty.
     *
     * @param availableProducts lista dostępnych produktów
     */
    private static void displayAvailableProducts(List<Product> availableProducts) {
        System.out.println(Colors.GREEN + "Dostępne produkty:" + Colors.RESET);
        for (int i = 0; i < availableProducts.size(); i++) {
            Product product = availableProducts.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " (cena: " + product.getPrice() + ")");
        }
    }

    /**
     * Dodaje produkt do koszyka.
     *
     * @param availableProducts lista dostępnych produktów
     * @param shoppingCart      koszyk zakupowy
     * @param scanner           obiekt Scanner do odczytu danych wejściowych
     */
    private static void addProductToCart(List<Product> availableProducts, ShoppingCart shoppingCart, Scanner scanner) {
        displayAvailableProducts(availableProducts);
        System.out.print(Colors.YELLOW + "Wybierz numer produktu, który chcesz dodać do koszyka: " + Colors.RESET);
        int selection = scanner.nextInt();
        if (selection >= 1 && selection <= availableProducts.size()) {
            Product selectedProduct = availableProducts.get(selection - 1);

            System.out.print(Colors.CYAN + "Podaj ilość: " + Colors.RESET);
            int quantity = scanner.nextInt();

            for (int i = 0; i < quantity; i++) {
                shoppingCart.addProduct(selectedProduct);
            }

            System.out.println(Colors.GREEN + "Produkt dodany do koszyka." + Colors.RESET);
        } else {
            System.out.println(Colors.RED + "Nieprawidłowy numer produktu." + Colors.RESET);
        }
    }

    /**
     * Wyświetla zawartość koszyka.
     *
     * @param shoppingCart koszyk zakupowy
     */
    private static void displayShoppingCart(ShoppingCart shoppingCart) {
        List<Product> cartProducts = shoppingCart.getProducts();

        if (cartProducts.isEmpty()) {
            System.out.println(Colors.BLUE + "Koszyk jest pusty." + Colors.RESET);
        } else {
            System.out.println(Colors.GREEN + "Zawartość koszyka:" + Colors.RESET);

            // Tworzenie mapy ilości produktów
            Map<Product, Integer> productQuantityMap = new HashMap<>();
            for (Product product : cartProducts) {
                productQuantityMap.put(product, productQuantityMap.getOrDefault(product, 0) + 1);
            }

            double totalPrice = 0.0; // Zmienna przechowująca kwotę całkowitą

            for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double productTotalPrice = quantity * product.getPrice();
                totalPrice += productTotalPrice;

                totalPrice = Math.round(totalPrice * 100.0) / 100.0; // Zaokrąglenie do dwóch miejsc po przecinku

                System.out.println(quantity + "x " + product.getName() + " (cena: " + product.getPrice() + ")");
            }

            System.out.println(Colors.CYAN + "Kwota do zapłaty: " + Colors.YELLOW + totalPrice + Colors.RESET);
        }
    }

    /**
     * Przetwarza produkty w koszyku.
     *
     * @param shoppingCart    koszyk zakupowy
     * @param productService serwis do przetwarzania produktów
     */
    private static void processProducts(ShoppingCart shoppingCart, ProductService productService) {
        System.out.println(Colors.GREEN + "Przetwarzanie produktów w koszyku:" + Colors.RESET);
        productService.processProduct(shoppingCart);
    }
}
