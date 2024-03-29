package com.twu.biblioteca.db;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Customer;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;

import java.util.List;

import static java.util.Arrays.asList;

public class LibraryDatabase {
    private static List<Book> books;
    private static List<Movie> movies;
    private static List<Customer> customers;

    public static List<Book> getBooksList() {
        if (null == books) {
            return books = asList(new Book().title("Effective Java").author("Joshua Bloch").releasedYear(1998),
                    new Book().title("Clean Code").author("Robert C. Martin").releasedYear(2006),
                    new Book().title("Java Concurrency in Practice").author("Brian Goetz").releasedYear(2000),
                    new Book().title("Head First Design Patterns").author("Eric Freeman & Elisabeth Robson").releasedYear(2002));
        }
        return books;
    }

    public static List<Movie> getMoviesList() {
        if (null == movies) {
            return movies = asList(new Movie().title("Interstellar").director("Christopher Nolan").year(2014).rating(Rating.NINE_STARS),
                    new Movie().title("Game of Thrones").director("David Benioff, D.B. Weiss").year(2011).rating(Rating.TEN_STARS),
                    new Movie().title("Gladiator").director("Ridley Scott").year(2000).rating(Rating.EIGHT_STARS),
                    new Movie().title("Braveheart").director("Mel Gibson").year(1995).rating(Rating.EIGHT_STARS));
        }
        return movies;
    }

    public static List<Customer> getCustomersList() {
        if (null == customers) {
            return customers = asList(new Customer("111-1111", "password", true, "Caetano", "caematos@gmail.com", "555-1245-231"),
                    new Customer("222-2222", "password", false, "Fulano", "fulano@fulano.com", "555-23132-5453"),
                    new Customer("333-3333", "password", false, "Beltrano", "beltrano@beltrano.com", "555-56464-456"));
        }
        return customers;
    }

    public static void setCustomers(List<Customer> customers) {
        LibraryDatabase.customers = customers;
    }
}
