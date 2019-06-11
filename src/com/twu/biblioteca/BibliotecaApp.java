package com.twu.biblioteca;

import com.twu.biblioteca.helper.LibraryHelper;
import com.twu.biblioteca.model.Menu;
import com.twu.biblioteca.model.Product;
import com.twu.biblioteca.service.BookService;

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
    public static final int MENU_OPTION_LIST_MOVIES = 4;

    private PrintStream outPrintStream;
    private BookService bookService = new BookService();

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
                printProductsList(new ArrayList<>(bookService.getAvailableProducts(new ArrayList<>(LibraryHelper.getBooksList()))));
                break;
            case MENU_OPTION_CHECKOUT_BOOK:
                bookService.findAndCheckoutBookByTitle(getBookTitleFromUser());
                break;
            case MENU_OPTION_RETURN_BOOK:
                bookService.findAndReturnBookByTitle(getBookTitleFromUser());
                break;
            case MENU_OPTION_LIST_MOVIES:
                printProductsList(new ArrayList<>(bookService.getAvailableProducts(new ArrayList<>(LibraryHelper.getMoviesList()))));
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
            outPrintStream.print(menuOption + "\n");
        }
    }

    public void printWelcomeMessage() {
        outPrintStream.println(getWelcomeMessage());
    }

    private void printProductsList(List<Product> products) {
        for (Product product : products) {
            outPrintStream.println(product);
        }
    }

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

}
