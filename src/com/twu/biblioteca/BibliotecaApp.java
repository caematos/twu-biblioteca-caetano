package com.twu.biblioteca;

import com.twu.biblioteca.controller.BookController;
import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Menu;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final int MENU_OPTION_LIST_BOOKS = 1;
    private static final int MENU_OPTION_QUIT = 0;
    private static final int MENU_OPTION_CHECKOUT_BOOK = 2;
    private static final int MENU_OPTION_RETURN_BOOK = 3;

    private PrintStream outPrintStream;
    private BookController bookController = new BookController();
    private BookHelper bookHelper = new BookHelper();
    private ArrayList<Book> booksList = bookHelper.getDummyBooksList();

    public BibliotecaApp(PrintStream outPrintStream) {
        this.outPrintStream = outPrintStream;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(System.out);
        app.startBiblioteca();
    }

    private void startBiblioteca() {
        printWelcomeMessage();

        int userChoice;
        do {
            displayOptionsMenu();
            userChoice = getUserChoice();
            executeUserChoice(userChoice);
        } while (userChoice != 0);
    }

    public void executeUserChoice(int userChoice) {
        String invalidOptionMessage = "Please, choose a valid option.";
        switch (userChoice) {
            case MENU_OPTION_LIST_BOOKS:
                printAvailableBooksList();
                break;
            case MENU_OPTION_CHECKOUT_BOOK:
                bookController.findAndCheckoutBookByTitle(getBookTitleFromUser(), booksList);
                break;
            case MENU_OPTION_RETURN_BOOK:
                bookController.findAndReturnBookByTitle(getBookTitleFromUser(), booksList);
                break;
            case MENU_OPTION_QUIT:
                break;
            default:
                outPrintStream.println(invalidOptionMessage);
        }
    }

    private String getBookTitleFromUser() {
        Scanner scanner = new Scanner(System.in);
        outPrintStream.println("Please, insert book title and press <return>: ");
        return scanner.nextLine();
    }

    public void printAvailableBooksList() {
         printBooksList(bookController.getAvailableBooks(booksList));
    }

    public int getUserChoice() {
        Scanner userInput = new Scanner(System.in);
        try {
            return userInput.nextInt();
        } catch (InputMismatchException e) {
            return 99;
        }
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

    private void printBooksList(List<Book> booksList) {
        for (Book book : booksList) {
            outPrintStream.println(book);
        }
    }

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

}
