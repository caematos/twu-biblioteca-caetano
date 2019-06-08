package com.twu.biblioteca.controller;

import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class BookControllerTest {

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
}