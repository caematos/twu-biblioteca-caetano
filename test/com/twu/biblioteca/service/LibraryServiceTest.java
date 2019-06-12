package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.CheckinNotAvailable;
import com.twu.biblioteca.exception.CheckoutNotAvailable;
import com.twu.biblioteca.exception.ProductNotFoundException;
import com.twu.biblioteca.db.LibraryDatabase;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Product;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class LibraryServiceTest {
    private Book testProductBook1;
    private LibraryService libraryService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpBooks() {
        testProductBook1 = new Book("testBook", "testAuthor", 2019);
        libraryService = new LibraryService();
    }

    @Test
    public void shouldOnlyListAvailableProducts() {
        //given (arrange)
        List<Book> books = LibraryDatabase.getBooksList();
        int initialAvailableBooks = libraryService.getAvailableProducts(new ArrayList<>(books)).size();

        //when (act)
        books.get(0).setAvailable(false);

        int finalAvailableBooks = libraryService.getAvailableProducts(new ArrayList<>(books)).size();

        //then (assert)
        assertEquals(finalAvailableBooks, initialAvailableBooks - 1);
    }

    @Test
    public void shouldOnlyListUnavailableProducts(){
        List<Book> booksList = LibraryDatabase.getBooksList();

        int initialAvailableBooks = booksList.size();

        booksList.get(0).setAvailable(false);
        booksList.get(1).setAvailable(false);

        int expectedUnavailableBooks = libraryService.getUnavailableProducts(new ArrayList<>(booksList)).size();

        assertEquals(2, expectedUnavailableBooks);

    }

    @Test
    public void shouldMarkAProductAsUnavailable() throws CheckoutNotAvailable {
        LibraryService libraryService = new LibraryService();

        libraryService.checkoutProduct(testProductBook1);

        assertFalse(testProductBook1.isAvailable());
    }

    @Test
    public void shouldMarkProductAsAvailable() throws CheckinNotAvailable {
        testProductBook1.setAvailable(false);

        libraryService.checkinProduct(testProductBook1);

        assertTrue(testProductBook1.isAvailable());
    }

    @Test
    public void shouldPassWhenSuccessfulCheckout() throws CheckoutNotAvailable {
        libraryService.checkoutProduct(testProductBook1);
    }

    @Test
    public void shouldThrowExceptionWhenInvalidCheckout() throws CheckoutNotAvailable {
        exception.expect(CheckoutNotAvailable.class);

        testProductBook1.setAvailable(false);
        libraryService.checkoutProduct(testProductBook1);
    }

    @Test
    public void shouldPassWhenSuccessfulCheckin() throws CheckinNotAvailable {
        testProductBook1.setAvailable(false);
        libraryService.checkinProduct(testProductBook1);
    }

    @Test
    public void shouldThrowErrorWhenInvalidCheckin() throws CheckinNotAvailable {
        exception.expect(CheckinNotAvailable.class);

        libraryService.checkinProduct(testProductBook1);
    }

    @Test
    public void shouldFindBookByTitle() throws ProductNotFoundException {
        //given
        List<Book> books = LibraryDatabase.getBooksList();

        //when
        String firstBookOfTheListTitle = books.get(0).getTitle();
        Book book = libraryService.findBookByTitle(books, firstBookOfTheListTitle);

        assertEquals(book.getTitle().toUpperCase(), firstBookOfTheListTitle.toUpperCase());
    }

    @Test
    public void shouldFindMovieByTitle() throws ProductNotFoundException {
        List<Movie> movies = LibraryDatabase.getMoviesList();

        String firstTitleOfTheList = movies.get(0).getTitle();
        Movie movie = libraryService.findMovieByTitle(movies, firstTitleOfTheList);

        assertEquals(movie.getTitle().toUpperCase(), firstTitleOfTheList.toUpperCase());
    }

    @Test
    public void shouldThrowProductNotFoundExceptionWhenProductIsNotFound() throws ProductNotFoundException {
        exception.expect(ProductNotFoundException.class);

        libraryService.findProductByTitle(new ArrayList<>(LibraryDatabase.getBooksList()), "");
    }

    @Test
    public void shouldGetAllProductsMarkedAsAvailable() {
        Book unavailableBook = new Book("", "", 2);
        unavailableBook.setAvailable(false);
        Book availableBook = new Book("", "", 2);

        List<Book> library = asList(unavailableBook, availableBook);
        List<Product> expectedBooksList = Collections.singletonList(availableBook);
        List<Product> actualBooksList = libraryService.getAvailableProducts(new ArrayList<>(library));

        assertEquals(actualBooksList.size(), 1);
        assertEquals(expectedBooksList, actualBooksList);
    }
}