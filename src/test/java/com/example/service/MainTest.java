package com.example.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.Main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void testAddProductToCart() {
        String input = "2\n1\n2\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        String expectedOutput = "Przetwarzanie produktów w koszyku:" + System.lineSeparator() +
                "Przetwarzanie zakończone." + System.lineSeparator() +
                System.lineSeparator() +
                "======= Menu =======" + System.lineSeparator() +
                "1. Wyświetl dostępne produkty" + System.lineSeparator() +
                "2. Dodaj produkt do koszyka" + System.lineSeparator() +
                "3. Podgląd koszyka" + System.lineSeparator() +
                "4. Edytuj koszyk" + System.lineSeparator() +
                "5. Przetwarzaj produkty w koszyku" + System.lineSeparator() +
                "6. Zakończ" + System.lineSeparator() +
                System.lineSeparator() +
                "Wybierz opcję: ";

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveProductFromCart() {
        String input = "3\n1\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        String expectedOutput = "Zawartość koszyka:\n" +
                "1x Mleko (cena: 2.59)\n" +
                "Suma cen: 2.59\n" +
                "\n" +
                "Wybierz numer produktu, który chcesz usunąć z koszyka (0 aby anulować):\n" +
                "1\n" +
                "Produkt usunięty z koszyka.\n" +
                "\n" +
                "======= Menu =======\n" +
                "1. Wyświetl dostępne produkty\n" +
                "2. Dodaj produkt do koszyka\n" +
                "3. Podgląd koszyka\n" +
                "4. Edytuj koszyk\n" +
                "5. Przetwarzaj produkty w koszyku\n" +
                "6. Zakończ\n" +
                "\n" +
                "Wybierz opcję: ";
        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testCalculateTotalPrice() {
        String input = "5\n6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        String expectedOutput = "Przetwarzanie produktów w koszyku:\n" +
                "Przetwarzanie zakończone.\n" +
                "\n" +
                "======= Menu =======\n" +
                "1. Wyświetl dostępne produkty\n" +
                "2. Dodaj produkt do koszyka\n" +
                "3. Podgląd koszyka\n" +
                "4. Edytuj koszyk\n" +
                "5. Przetwarzaj produkty w koszyku\n" +
                "6. Zakończ\n" +
                "\n" +
                "Wybierz opcję: ";
        Assertions.assertEquals(expectedOutput, outContent.toString());
    }
}
