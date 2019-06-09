package com.twu.biblioteca.controller;

import com.twu.biblioteca.exception.BookNotFoundException;
import com.twu.biblioteca.model.Book;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookController {
    private static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the book";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid book to return";

    private PrintStream systemOut;

    public BookController() {
        this.systemOut = System.out;
    }

    public BookController(PrintStream systemOut) {
        this.systemOut = systemOut;
    }

    public ArrayList<Book> getAvailableBooks(ArrayList<Book> bookList) {
        return (ArrayList<Book>) bookList.stream().filter(b -> b.isAvailable()).collect(Collectors.toList());
    }

    public void checkoutBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            systemOut.println(CHECKOUT_SUCCESS_MESSAGE);
        } else {
            systemOut.println(CHECKOUT_ERROR_MESSAGE);
        }
    }

    public void returnBook(Book book) {
        if (!book.isAvailable()) {
            book.setAvailable(true);
            systemOut.println(RETURN_SUCCESS_MESSAGE);
        } else {
            systemOut.println(RETURN_ERROR_MESSAGE);
        }

    }

    public Book getBookByTitle(ArrayList<Book> dummyBookList, String bookTitleQuery) throws BookNotFoundException {
        Optional<Book> firstBookMatch = dummyBookList.stream().filter(c -> c.getTitle().equalsIgnoreCase(bookTitleQuery)).findFirst();

        if (!firstBookMatch.isPresent()) {
            throw new BookNotFoundException();
        }

        return firstBookMatch.get();
    }
}
