package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.CheckinNotAvailable;
import com.twu.biblioteca.exception.CheckoutNotAvailable;
import com.twu.biblioteca.exception.ProductNotFoundException;
import com.twu.biblioteca.helper.LibraryHelper;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Product;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryService {
    private static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the book";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid book to return";

    private PrintStream outPrintStream;

    public LibraryService() {
        this.outPrintStream = System.out;
    }

    public LibraryService(PrintStream systemOut) {
        this.outPrintStream = systemOut;
    }

    public List<Book> getAvailableBooks(List<Book> bookList) {
        return bookList.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Product> getAvailableProducts(List<Product> products) {
        return products.stream().filter(Product::isAvailable).collect(Collectors.toList());
    }

    public void checkoutBook(Book book) throws CheckoutNotAvailable {
        if (!book.isAvailable()) {
            throw new CheckoutNotAvailable();
        }
            book.setAvailable(false);
    }

    public void returnBook(Book book) throws CheckinNotAvailable {
        if (book.isAvailable()) {
            throw new CheckinNotAvailable();
        }
        book.setAvailable(true);
    }

    public void findAndReturnBookByTitle(String bookTitle) {
        try {
            returnBook(getBookByTitle(LibraryHelper.getBooksList(), bookTitle));
            outPrintStream.println(RETURN_SUCCESS_MESSAGE);
        } catch (ProductNotFoundException e) {
            outPrintStream.println("Book not found, please try again.");
        } catch (CheckinNotAvailable e) {
            outPrintStream.println(RETURN_ERROR_MESSAGE);
        }
    }

    public void findAndCheckoutBookByTitle(String bookTitle) {
        try {
            checkoutBook(getBookByTitle(LibraryHelper.getBooksList(), bookTitle));
            outPrintStream.println(CHECKOUT_SUCCESS_MESSAGE);
        } catch (ProductNotFoundException e) {
            outPrintStream.println("Book not found, please try again.");
        } catch (CheckoutNotAvailable e) {
            outPrintStream.println(CHECKOUT_ERROR_MESSAGE);
        }
    }

    public Book getBookByTitle(List<Book> dummyBookList, String bookTitleQuery) throws ProductNotFoundException {
        Optional<Book> firstBookMatch = dummyBookList.stream().filter(c -> c.getTitle().equalsIgnoreCase(bookTitleQuery)).findFirst();

        if (!firstBookMatch.isPresent()) {
            throw new ProductNotFoundException();
        }

        return firstBookMatch.get();
    }
}
