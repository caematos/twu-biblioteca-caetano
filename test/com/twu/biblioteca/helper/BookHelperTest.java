package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookHelperTest {

    @Test
    public void shouldReturnAListOfDummyBooks() {
        BookHelper bookHelper = new BookHelper();
        ArrayList<Book> expectedBooks = getExpectedBooks();

        assertThat(bookHelper.getDummyBooksList(), is(equalTo(expectedBooks)));
    }

    private ArrayList<Book> getExpectedBooks() {
        ArrayList<Book> dummyBookList = new ArrayList<>();
        dummyBookList.add(new Book("Effective Java", "Robert C. Martin"));
        dummyBookList.add(new Book("Clean Code", "Joshua Bloch"));
        dummyBookList.add(new Book("Java Concurrency in Practice", "Brian Goetz"));

        return dummyBookList;
    }
}