package com.twu.biblioteca;

import com.twu.biblioteca.db.LibraryDatabase;
import com.twu.biblioteca.exception.*;
import com.twu.biblioteca.model.Menu;
import com.twu.biblioteca.model.Product;
import com.twu.biblioteca.service.LibraryService;
import com.twu.biblioteca.service.LoginService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the product.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that product is not available";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the product";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid product to return";
    public static final String PRODUCT_NOT_FOUND = "Product not found, please try again.";

    private static final int MENU_OPTION_QUIT = 0;
    private static final int MENU_OPTION_LIST_BOOKS = 1;
    private static final int MENU_OPTION_CHECKOUT_BOOK = 2;
    private static final int MENU_OPTION_RETURN_BOOK = 3;
    private static final int MENU_OPTION_LIST_MOVIES = 4;
    private static final int MENU_OPTION_CHECKOUT_MOVIE = 5;
    private static final int MENU_OPTION_CHECKIN_MOVIE = 6;
    public static final String TYPE_TITLE = "title";
    public static final int MENU_OPTION_VIEW_CHECKEDOUT_PRODUCTS = 7;

    private PrintStream outPrintStream;
    private LibraryService libraryService = new LibraryService();
    private LoginService loginService = new LoginService();

    public BibliotecaApp(PrintStream outPrintStream) {
        this.outPrintStream = outPrintStream;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(System.out);
        app.startBiblioteca();
    }

    private void startBiblioteca() {
        if (confirmLogin()) {
            printWelcomeMessage();
            int userChoice;
            do {
                displayOptionsMenu();
                userChoice = getUserChoice();
                executeUserChoice(userChoice);
            } while (userChoice != 0);
        }
    }

    private boolean confirmLogin() {
        String login = getInputFromUser("your login");
        String password = getInputFromUser("your password");
        try {
            return loginService.login(login, password);
        } catch (CustomerNotFoundException e) {
            outPrintStream.println("Invalid Login. Bye!");
            return false;
        } catch (InvalidPasswordException e) {
            outPrintStream.println("Invalid Password. Bye!");
            return false;
        }
    }

    public void executeUserChoice(int userChoice) {
        String invalidOptionMessage = "Please, choose a valid option.";
        switch (userChoice) {
            case MENU_OPTION_LIST_BOOKS:
                printProductsList(new ArrayList<>(libraryService.getAvailableProducts(new ArrayList<>(LibraryDatabase.getBooksList()))));
                break;
            case MENU_OPTION_CHECKOUT_BOOK:
                findAndCheckoutProductByTitle(new ArrayList<>(LibraryDatabase.getBooksList()), getInputFromUser(TYPE_TITLE));
                break;
            case MENU_OPTION_RETURN_BOOK:
                findAndCheckinProductByTitle(new ArrayList<>(LibraryDatabase.getBooksList()), getInputFromUser(TYPE_TITLE));
                break;
            case MENU_OPTION_LIST_MOVIES:
                printProductsList(new ArrayList<>(libraryService.getAvailableProducts(new ArrayList<>(LibraryDatabase.getMoviesList()))));
                break;
            case MENU_OPTION_CHECKOUT_MOVIE:
                findAndCheckoutProductByTitle(new ArrayList<>(LibraryDatabase.getMoviesList()), getInputFromUser(TYPE_TITLE));
                break;
            case MENU_OPTION_CHECKIN_MOVIE:
                findAndCheckinProductByTitle(new ArrayList<>(LibraryDatabase.getMoviesList()), getInputFromUser(TYPE_TITLE));
                break;
            case MENU_OPTION_VIEW_CHECKEDOUT_PRODUCTS:
                printCheckedOutProducts();
                break;
            case MENU_OPTION_QUIT:
                break;
            default:
                outPrintStream.println(invalidOptionMessage);
        }
    }

    private void printCheckedOutProducts() {
        List<Product> movies = new ArrayList<>(libraryService.getUnavailableProducts(new ArrayList<>(LibraryDatabase.getMoviesList())));
        List<Product> books = new ArrayList<>(libraryService.getUnavailableProducts(new ArrayList<>(LibraryDatabase.getBooksList())));
        if (movies.size() > 0) {
            outPrintStream.println("\nChecked out Movies:");
            printCheckedOutProducts(movies);
        }
        if (books.size() > 0) {
            outPrintStream.println("\nChecked out Books:");
            printCheckedOutProducts(books);
        }
    }

    private void printCheckedOutProducts(List<Product> products) {
        for (Product product : products) {
            outPrintStream.println(product.toString());
        }
    }

    private String getInputFromUser(String type) {
        Scanner scanner = new Scanner(System.in);
        outPrintStream.println("Please, insert " + type + " and press <return>: ");
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
            outPrintStream.print(menuOption + " - ");
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

    public void findAndCheckinProductByTitle(List<Product> products, String title) {
        try {
            libraryService.checkinProduct(libraryService.findProductByTitle(products, title));
            outPrintStream.println(RETURN_SUCCESS_MESSAGE);
        } catch (ProductNotFoundException e) {
            outPrintStream.println(PRODUCT_NOT_FOUND);
        } catch (CheckinNotAvailable e) {
            outPrintStream.println(RETURN_ERROR_MESSAGE);
        }
    }

    public void findAndCheckoutProductByTitle(List<Product> products, String title) {
        try {
            libraryService.checkoutProduct(libraryService.findProductByTitle(products, title));
            outPrintStream.println(CHECKOUT_SUCCESS_MESSAGE);
        } catch (ProductNotFoundException e) {
            outPrintStream.println(PRODUCT_NOT_FOUND);
        } catch (CheckoutNotAvailable e) {
            outPrintStream.println(CHECKOUT_ERROR_MESSAGE);
        }
    }

}
