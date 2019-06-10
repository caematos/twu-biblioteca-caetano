package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.BookCheckinNotAvailable;
import com.twu.biblioteca.exception.BookCheckoutNotAvailable;
import com.twu.biblioteca.exception.BookNotFoundException;
import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {
    private static final String CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the book";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid book to return";

    private PrintStream outPrintStream;

    public BookService() {
        this.outPrintStream = System.out;
    }

    public BookService(PrintStream systemOut) {
        this.outPrintStream = systemOut;
    }

    public List<Book> getAvailableBooks(List<Book> bookList) {
        return bookList.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public void checkoutBook(Book book) throws BookCheckoutNotAvailable {
        if (!book.isAvailable()) {
            throw new BookCheckoutNotAvailable();
        }
            book.setAvailable(false);
    }

    public void returnBook(Book book) throws BookCheckinNotAvailable {
        if (book.isAvailable()) {
            throw new BookCheckinNotAvailable();
        }
        book.setAvailable(true);
    }

    public void findAndReturnBookByTitle(String bookTitle) {
        try {
            returnBook(getBookByTitle(BookHelper.getBooksList(), bookTitle));
            outPrintStream.println(RETURN_SUCCESS_MESSAGE);
        } catch (BookNotFoundException e) {
            outPrintStream.println("Book not found, please try again.");
        } catch (BookCheckinNotAvailable e) {
            outPrintStream.println(RETURN_ERROR_MESSAGE);
        }
    }

    public void findAndCheckoutBookByTitle(String bookTitle) {
        try {
            checkoutBook(getBookByTitle(BookHelper.getBooksList(), bookTitle));
            outPrintStream.println(CHECKOUT_SUCCESS_MESSAGE);
        } catch (BookNotFoundException e) {
            outPrintStream.println("Book not found, please try again.");
        } catch (BookCheckoutNotAvailable e) {
            outPrintStream.println(CHECKOUT_ERROR_MESSAGE);
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
