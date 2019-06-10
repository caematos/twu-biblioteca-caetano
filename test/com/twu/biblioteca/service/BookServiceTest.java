package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.BookCheckinNotAvailable;
import com.twu.biblioteca.exception.BookCheckoutNotAvailable;
import com.twu.biblioteca.exception.BookNotFoundException;
import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import org.junit.After;
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
    private ByteArrayOutputStream outSpy;
    private BookService bookService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpBooks() {
        testBook1 = new Book("testBook", "testAuthor", 2019);
        outSpy = new ByteArrayOutputStream();
        bookService = new BookService(new PrintStream(outSpy));
    }

    @After
    @Test
    public void shouldOnlyListAvailableBooks() {
        //given (arrange)
        List<Book> bookList = BookHelper.getBooksList();
        int initialAvailableBooks = bookService.getAvailableBooks(bookList).size();

        //when (act)
        bookList.get(0).setAvailable(false);

        int finalAvailableBooks = bookService.getAvailableBooks(bookList).size();

        //then (assert)
        assertEquals(finalAvailableBooks, initialAvailableBooks - 1);
    }

    @Test
    public void shouldMarkABookAsUnavailable() throws BookCheckoutNotAvailable {
        BookService bookService = new BookService();

        bookService.checkoutBook(testBook1);

        assertFalse(testBook1.isAvailable());
    }

    @Test
    public void shouldMarkBookAsAvailable() throws BookCheckinNotAvailable {
        testBook1.setAvailable(false);

        bookService.returnBook(testBook1);

        assertTrue(testBook1.isAvailable());
    }

    @Test
    public void shouldPassWhenSuccessfulCheckout() throws BookCheckoutNotAvailable {
        bookService.checkoutBook(testBook1);
    }

    @Test
    public void shouldThrowExceptionWhenInvalidCheckout() throws BookCheckoutNotAvailable {
        exception.expect(BookCheckoutNotAvailable.class);

        testBook1.setAvailable(false);
        bookService.checkoutBook(testBook1);
    }

    @Test
    public void shouldPassWhenSuccessfulReturn() throws BookCheckinNotAvailable {
        testBook1.setAvailable(false);
        bookService.returnBook(testBook1);
    }

    @Test
    public void shouldThrowErrorWhenInvalidReturn() throws BookCheckinNotAvailable {
        exception.expect(BookCheckinNotAvailable.class);

        bookService.returnBook(testBook1);
    }

    @Test
    public void shouldFindBookByTitle() throws BookNotFoundException {
        //given
        List<Book> dummyBookList = BookHelper.getBooksList();

        //when
        String firstBookOfTheListTitle = dummyBookList.get(0).getTitle();
        Book book = bookService.getBookByTitle(dummyBookList, firstBookOfTheListTitle);

        assertEquals(book.getTitle().toUpperCase(), firstBookOfTheListTitle.toUpperCase());
    }


    @Test
    public void shouldThrowBookNotFoundExceptionWhenBookIsNotFound() throws BookNotFoundException {
        exception.expect(BookNotFoundException.class);

        bookService.getBookByTitle(BookHelper.getBooksList(), "");
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