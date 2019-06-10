package com.twu.biblioteca.controller;

import com.twu.biblioteca.exception.BookNotFoundException;
import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.util.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BookControllerTest {
    private Book testBook1;
    private ByteArrayOutputStream outSpy;
    private BookController bookController;
    private BookHelper bookHelper;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpBooks() {
        testBook1 = new Book("testBook", "testAuthor", 2019);
        outSpy = new ByteArrayOutputStream();
        bookController = new BookController(new PrintStream(outSpy));
        bookHelper = new BookHelper();
    }

    @After
    @Test
    public void shouldOnlyListAvailableBooks() {
        //given
        ArrayList<Book> bookList = bookHelper.getDummyBooksList();
        int initialAvailableBooks = bookController.getAvailableBooks(bookList).size();

        //when
        bookList.get(0).setAvailable(false);

        int finalAvailableBooks = bookController.getAvailableBooks(bookList).size();

        //then
        assertTrue(finalAvailableBooks == initialAvailableBooks - 1);

    }

    @Test
    public void shouldMarkABookAsUnavailable() {
        //given
        BookController bookController = new BookController();

        bookController.checkoutBook(testBook1);

        assertFalse(testBook1.isAvailable());
    }

    @Test
    public void shouldMarkABookAsAvailable() {
        //given
        testBook1.setAvailable(false);

        bookController.returnBook(testBook1);

        assertTrue(testBook1.isAvailable());
    }

    @Test
    public void shouldReturnSuccessMessageOnSuccessfulCheckout() {
        bookController.checkoutBook(testBook1);
        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Thank you! Enjoy the book.");
    }

    @Test
    public void shouldReturnErrorMessageOnSuccessfulCheckout() {
        testBook1.setAvailable(false);
        bookController.checkoutBook(testBook1);

        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Sorry, that book is not available");
    }

    @Test
    public void shouldReturnSuccessMessageOnSuccessfulReturn() {
        testBook1.setAvailable(false);
        bookController.returnBook(testBook1);

        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Thank you for returning the book");
    }

    @Test
    public void shouldReturnErrorMessageOnSuccessfulReturn() {
        bookController.returnBook(testBook1);
        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "That is not a valid book to return");
    }

    @Test
    public void shouldFindBookByTitle() throws BookNotFoundException {
        //given
        ArrayList<Book> dummyBookList = bookHelper.getDummyBooksList();

        //when
        String firstBookOfTheListTitle = dummyBookList.get(0).getTitle();
        Book book = bookController.getBookByTitle(dummyBookList, firstBookOfTheListTitle);

        assertEquals(book.getTitle().toUpperCase(), firstBookOfTheListTitle.toUpperCase());
    }


    @Test
    public void shouldThrowBookNotFoundExceptionWhenBookIsNotFound() throws BookNotFoundException {

        exception.expect(BookNotFoundException.class);

        bookController.getBookByTitle(bookHelper.getDummyBooksList(), "");

    }

    @Test
    public void shouldGetAllBooksMarkedAsAvailable() {
        Book unavailableBook = new Book("", "", 2);
        unavailableBook.setAvailable(false);
        Book availableBook = new Book("", "", 2);

        List<Book> library = asList(unavailableBook, availableBook);
        List<Book> expectedBooksList = Collections.singletonList(availableBook);
        List<Book> actualBooksList = bookController.getAvailableBooks(library);

        assertEquals(actualBooksList.size(), 1);
        assertEquals(expectedBooksList, actualBooksList);
    }
}