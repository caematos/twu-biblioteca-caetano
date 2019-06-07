package com.twu.biblioteca;

import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final int MENU_OPTION_LIST_BOOKS = 1;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        Menu menu = new Menu();
        app.printWelcomeMessage();
        app.displayMenuOptions(menu);

        int userChoice = app.getUserChoice();

        app.executeUserChoice(app, userChoice);

    }

    private void executeUserChoice(BibliotecaApp app, int userChoice) {
        switch (userChoice) {
            case MENU_OPTION_LIST_BOOKS:
                app.printBooksList();
                break;
            default:
                System.out.println("Please, choose a valid option.");
        }
    }

    public int getUserChoice() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextInt();
    }

    private void displayMenuOptions(Menu menu) {
        for (String menuOption : menu.getMenuOptions()) {
            System.out.println(menuOption);
        }
    }

    private void printWelcomeMessage() {
        System.out.println(getWelcomeMessage());
    }

    private void printBooksList() {
        BookHelper bookHelper = new BookHelper();
        ArrayList<Book> dummyBooksList = bookHelper.getDummyBooksList();

        for (Book book : dummyBooksList) {
            System.out.println(book);
        }
    }

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


}
