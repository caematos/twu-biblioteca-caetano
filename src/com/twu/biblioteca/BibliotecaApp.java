package com.twu.biblioteca;

import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;

import java.util.ArrayList;

public class BibliotecaApp {

    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {

        printWelcomeMessage();
        printBooksList();
    }

    private static void printWelcomeMessage() {
        System.out.println(getWelcomeMessage());
    }

    private static void printBooksList() {
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
