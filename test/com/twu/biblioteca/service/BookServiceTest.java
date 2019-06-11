package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.CheckinNotAvailable;
import com.twu.biblioteca.exception.CheckoutNotAvailable;
import com.twu.biblioteca.exception.ProductNotFoundException;
import com.twu.biblioteca.helper.LibraryHelper;
import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BookServiceTest {
    private Book testBook1;
    private BookService bookService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpBooks() {
        testBook1 = new Book("testBook", "testAuthor", 2019);
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        bookService = new BookService(new PrintStream(outSpy));
    }

    @Test
    public void shouldOnlyListAvailableBooks() {
        //given (arrange)
        List<Book> bookList = LibraryHelper.getBooksList();
        int initialAvailableBooks = bookService.getAvailableBooks(bookList).size();

        //when (act)
        bookList.get(0).setAvailable(false);

        int finalAvailableBooks = bookService.getAvailableBooks(bookList).size();

        //then (assert)
        assertEquals(finalAvailableBooks, initialAvailableBooks - 1);
    }

    @Test
    public void shouldMarkABookAsUnavailable() throws CheckoutNotAvailable {
        BookService bookService = new BookService();

        bookService.checkoutBook(testBook1);

        assertFalse(testBook1.isAvailable());
    }

    @Test
    public void shouldMarkBookAsAvailable() throws CheckinNotAvailable {
        testBook1.setAvailable(false);

        bookService.returnBook(testBook1);

        assertTrue(testBook1.isAvailable());
    }

    @Test
    public void shouldPassWhenSuccessfulCheckout() throws CheckoutNotAvailable {
        bookService.checkoutBook(testBook1);
    }

    @Test
    public void shouldThrowExceptionWhenInvalidCheckout() throws CheckoutNotAvailable {
        exception.expect(CheckoutNotAvailable.class);

        testBook1.setAvailable(false);
        bookService.checkoutBook(testBook1);
    }

    @Test
    public void shouldPassWhenSuccessfulReturn() throws CheckinNotAvailable {
        testBook1.setAvailable(false);
        bookService.returnBook(testBook1);
    }

    @Test
    public void shouldThrowErrorWhenInvalidReturn() throws CheckinNotAvailable {
        exception.expect(CheckinNotAvailable.class);

        bookService.returnBook(testBook1);
    }

    @Test
    public void shouldFindBookByTitle() throws ProductNotFoundException {
        //given
        List<Book> dummyBookList = LibraryHelper.getBooksList();

        //when
        String firstBookOfTheListTitle = dummyBookList.get(0).getTitle();
        Book book = bookService.getBookByTitle(dummyBookList, firstBookOfTheListTitle);

        assertEquals(book.getTitle().toUpperCase(), firstBookOfTheListTitle.toUpperCase());
    }


    @Test
    public void shouldThrowBookNotFoundExceptionWhenBookIsNotFound() throws ProductNotFoundException {
        exception.expect(ProductNotFoundException.class);

        bookService.getBookByTitle(LibraryHelper.getBooksList(), "");
    }

    @Test
    public void shouldGetAllBooksMarkedAsAvailable() {
        Book unavailableBook = new Book("", "", 2);
        unavailableBook.setAvailable(false);
        Book availableBook = new Book("", "", 2);

        List<Book> library = asList(unavailableBook, availableBook);
        List<Book> expectedBooksList = Collections.singletonList(availableBook);
        List<Book> actualBooksList = bookService.getAvailableBooks(library);

        assertEquals(actualBooksList.size(), 1);
        assertEquals(expectedBooksList, actualBooksList);
    }
}