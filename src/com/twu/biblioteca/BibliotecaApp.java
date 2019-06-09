package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookController;
import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Menu;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final int MENU_OPTION_LIST_BOOKS = 1;
    private static final int MENU_OPTION_QUIT = 0;

    private PrintStream outPrintStream;

    public BibliotecaApp(PrintStream outPrintStream) {
        this.outPrintStream = outPrintStream;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(System.out);

        app.printWelcomeMessage();

        int userChoice;

        do {
            app.displayOptionsMenu();
            userChoice = app.getUserChoice();
            app.executeUserChoice(userChoice);
        } while (userChoice != 0);

    }

    public void executeUserChoice(int userChoice) {
        String invalidOptionMessage = "Please, choose a valid option.";

        switch (userChoice) {
            case MENU_OPTION_LIST_BOOKS:
                printAvailableBooksList();
                break;
            case MENU_OPTION_QUIT:
                break;
            default:
                outPrintStream.println(invalidOptionMessage);
        }
    }

    public void printAvailableBooksList() {
         printBooksList(new BookController().getAvailableBooks(new BookHelper().getDummyBooksList()));
    }

    public int getUserChoice() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextInt();
    }

    public void displayOptionsMenu() {
        outPrintStream.println();
        for (String menuOption : new Menu().getMenuOptions()) {
            outPrintStream.print(menuOption + " - ");
        }
    }

    public void printWelcomeMessage() {
        outPrintStream.println(getWelcomeMessage());
    }

    private void printBooksList(ArrayList<Book> booksList) {
        for (Book book : booksList) {
            outPrintStream.println(book);
        }
    }

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

}
