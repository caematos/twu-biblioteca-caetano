package com.twu.biblioteca.controller;

import com.twu.biblioteca.exception.BookNotFoundException;
import com.twu.biblioteca.model.Book;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookController {
    private static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the book";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid book to return";

    private PrintStream outPrintStream;

    public BookController() {
        this.outPrintStream = System.out;
    }

    public BookController(PrintStream systemOut) {
        this.outPrintStream = systemOut;
    }

    public List<Book> getAvailableBooks(List<Book> bookList) {
        return bookList.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public void checkoutBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            outPrintStream.println(CHECKOUT_SUCCESS_MESSAGE);
        } else {
            outPrintStream.println(CHECKOUT_ERROR_MESSAGE);
        }
    }

    public void returnBook(Book book) {
        if (!book.isAvailable()) {
            book.setAvailable(true);
            outPrintStream.println(RETURN_SUCCESS_MESSAGE);
        } else {
            outPrintStream.println(RETURN_ERROR_MESSAGE);
        }
    }

    public void findAndReturnBookByTitle(String bookTitle, List<Book> bookList) {
        try {
            returnBook(getBookByTitle(bookList, bookTitle));
        } catch (BookNotFoundException e) {
            outPrintStream.println("Book not found, please try again.");
        }
    }

    public void findAndCheckoutBookByTitle(String bookTitle, List<Book> bookList) {
        try {
            checkoutBook(getBookByTitle(bookList, bookTitle));
        } catch (BookNotFoundException e) {
            outPrintStream.println("Book not found, please try again.");
        }
    }

    public Book getBookByTitle(List<Book> dummyBookList, String bookTitleQuery) throws BookNotFoundException {
        Optional<Book> firstBookMatch = dummyBookList.stream().filter(c -> c.getTitle().equalsIgnoreCase(bookTitleQuery)).findFirst();

        if (!firstBookMatch.isPresent()) {
            throw new BookNotFoundException();
        }

        return firstBookMatch.get();
    }
}
