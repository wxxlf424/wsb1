package com.example;

/**
 * Klasa Colors definiuje stałe reprezentujące różne kolory w terminalu, które można używać do formatowania tekstu w wyjściu konsoli. 
 * Każda zmienna statyczna przechowuje sekwencję ucieczki ANSI, która odpowiada danemu kolorowi. 
 */
public class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
}
