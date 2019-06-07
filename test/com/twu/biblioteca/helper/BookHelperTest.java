package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class BookHelperTest {

    @Test
    public void shouldReturnAtLeastOneBook() {
        BookHelper bookHelper = new BookHelper();
        ArrayList<Book> booksList = bookHelper.getDummyBooksList();

        assertTrue(booksList.size() > 0);
    }

    private ArrayList<Book> getExpectedBooks() {
        ArrayList<Book> dummyBookList = new ArrayList<>();
        dummyBookList.add(new Book("Effective Java", "Robert C. Martin"));
        dummyBookList.add(new Book("Clean Code", "Joshua Bloch"));
        dummyBookList.add(new Book("Java Concurrency in Practice", "Brian Goetz"));

        return dummyBookList;
    }
}