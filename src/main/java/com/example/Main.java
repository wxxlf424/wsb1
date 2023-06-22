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

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);

        List<Product> availableProducts = readProductsFromFile("products.txt");

        while (true) {
            System.out.println("======= Menu =======");
            System.out.println("1. Wyświetl dostępne produkty");
            System.out.println("2. Dodaj produkt do koszyka");
            System.out.println("3. Podgląd koszyka");
            System.out.println("4. Edytuj koszyk");
            System.out.println("5. Przetwarzaj produkty w koszyku");
            System.out.println("6. Zakończ");

            System.out.print("Wybierz opcję: ");
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
                    editShoppingCart(shoppingCart, scanner);
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
            System.out.println("Błąd odczytu pliku: " + e.getMessage());
        }

        return products;
    }

    private static void displayAvailableProducts(List<Product> availableProducts) {
        System.out.println("Dostępne produkty:");
        for (int i = 0; i < availableProducts.size(); i++) {
            Product product = availableProducts.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " (cena: " + product.getPrice() + ")");
        }
    }

    private static void addProductToCart(List<Product> availableProducts, ShoppingCart shoppingCart, Scanner scanner) {
        System.out.println("Wybierz numer produktu, który chcesz dodać do koszyka:");
        displayAvailableProducts(availableProducts);

        int selection = scanner.nextInt();
        if (selection >= 1 && selection <= availableProducts.size()) {
            Product selectedProduct = availableProducts.get(selection - 1);

            System.out.print("Podaj ilość: ");
            int quantity = scanner.nextInt();

            for (int i = 0; i < quantity; i++) {
                shoppingCart.addProduct(selectedProduct);
            }

            System.out.println("Produkt dodany do koszyka.");
        } else {
            System.out.println("Nieprawidłowy numer produktu.");
        }
    }

    private static void displayShoppingCart(ShoppingCart shoppingCart) {
        List<Product> cartProducts = shoppingCart.getProducts();

        if (cartProducts.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
        } else {
            System.out.println("Zawartość koszyka:");

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

                System.out.println(quantity + "x " + product.getName() + " (cena: " + product.getPrice() + ")");
            }

            System.out.println("Kwota do zapłaty: " + totalPrice);
        }
    }
    
    private static void editShoppingCart(ShoppingCart shoppingCart, Scanner scanner) {
        List<Product> cartProducts = shoppingCart.getProducts();

        if (cartProducts.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }

        System.out.println("Zawartość koszyka:");
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        for (Product product : cartProducts) {
            productQuantityMap.put(product, productQuantityMap.getOrDefault(product, 0) + 1);
        }

        int index = 1;
        for (Product product : cartProducts) {
            int quantity = productQuantityMap.get(product);
            System.out.println(index + ". " + quantity + "x " + product.getName() + " (cena: " + product.getPrice() + ")");
            index++;
        }

        System.out.println("Wybierz numer produktu, który chcesz usunąć z koszyka (0 aby anulować):");
        int selection = scanner.nextInt();

        if (selection >= 1 && selection <= cartProducts.size()) {
            Product selectedProduct = cartProducts.get(selection - 1);
            shoppingCart.getProducts().remove(selectedProduct);
            System.out.println("Produkt usunięty z koszyka.");
        } else if (selection == 0) {
            System.out.println("Anulowano.");
        } else {
            System.out.println("Nieprawidłowy numer produktu.");
        }
    }

    private static void processProducts(ShoppingCart shoppingCart, ProductService productService) {
        System.out.println("Przetwarzanie produktów w koszyku:");
        System.out.println("Czy chcesz zatwierdzić koszyk? (T/N): ");
        
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
        scanner.close();
        
        if (response.equalsIgnoreCase("T")) {
            System.out.println("Zakup został zaakceptowany.");
            shoppingCart.clearCart();
        } else {
            System.out.println("Zakup został anulowany.");
        }
    }
}
