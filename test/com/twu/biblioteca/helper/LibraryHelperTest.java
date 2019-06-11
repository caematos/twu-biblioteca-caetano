package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LibraryHelperTest {

    @Test
    public void shouldReturnAtLeastOneBook() {
        List<Book> booksList = LibraryHelper.getBooksList();

        assertTrue(booksList.size() > 0);
    }

    @Test
    public void shouldReturnAtLeastOneMovie() {
        List<Movie> movies = LibraryHelper.getMoviesList();

        assertTrue(movies.size() > 0);
    }

}