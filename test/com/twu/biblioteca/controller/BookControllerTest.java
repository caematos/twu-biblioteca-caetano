package com.twu.biblioteca.controller;

import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.util.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookControllerTest {
    Book testBook1;

    @Before
    public void setUpBooks() {
        testBook1 = new Book("testBook", "testAuthor", 2019);
    }
    @After
    @Test
    public void shouldOnlyListAvailableBooks() {
        //given
        BookController bookController = new BookController();
        BookHelper helper = new BookHelper();
        int initialAvailableBooks = 0;
        int finalAvailableBooks = 0;

        ArrayList<Book> bookList = helper.getDummyBooksList();

        //when
        initialAvailableBooks = bookController.getAvailableBooks(bookList).size();
        bookList.get(0).setAvailable(false);

        finalAvailableBooks = bookController.getAvailableBooks(bookList).size();

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
        BookController bookController = new BookController();
        testBook1.setAvailable(false);

        bookController.returnBook(testBook1);

        assertTrue(testBook1.isAvailable());
    }

    @Test
    public void shouldReturnSuccessMessageOnSuccessfulCheckout() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        BookController controller = new BookController(new PrintStream(outSpy));

        controller.checkoutBook(testBook1);
        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Thank you! Enjoy the book.");
    }

    @Test
    public void shouldReturnErrorMessageOnSuccessfulCheckout() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        BookController controller = new BookController(new PrintStream(outSpy));

        testBook1.setAvailable(false);
        controller.checkoutBook(testBook1);
        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Sorry, that book is not available");
    }  @Test

    public void shouldReturnSuccessMessageOnSuccessfulReturn() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        BookController controller = new BookController(new PrintStream(outSpy));

        testBook1.setAvailable(false);
        controller.returnBook(testBook1);

        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Thank you for returning the book");
    }

    @Test
    public void shouldReturnErrorMessageOnSuccessfulReturn() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        BookController controller = new BookController(new PrintStream(outSpy));

        controller.returnBook(testBook1);
        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "That is not a valid book to return");
    }
}