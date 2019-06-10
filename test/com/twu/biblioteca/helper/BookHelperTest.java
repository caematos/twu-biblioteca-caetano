package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BookHelperTest {

    @Test
    public void shouldReturnAtLeastOneBook() {
        List<Book> booksList = BookHelper.getBooksList();

        assertTrue(booksList.size() > 0);
    }

}