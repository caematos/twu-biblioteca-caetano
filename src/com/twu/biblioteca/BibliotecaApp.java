package com.twu.biblioteca;

public class BibliotecaApp {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        System.out.println(WELCOME_MESSAGE);
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
