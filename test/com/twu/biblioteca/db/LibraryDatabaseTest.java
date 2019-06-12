package com.twu.biblioteca.db;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LibraryDatabaseTest {

    @Test
    public void shouldReturnAtLeastOneBook() {
        List<Book> booksList = LibraryDatabase.getBooksList();

        assertTrue(booksList.size() > 0);
    }

    @Test
    public void shouldReturnAtLeastOneMovie() {
        List<Movie> movies = LibraryDatabase.getMoviesList();

        assertTrue(movies.size() > 0);
    }

}