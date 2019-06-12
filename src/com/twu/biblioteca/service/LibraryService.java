package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.CheckinNotAvailable;
import com.twu.biblioteca.exception.CheckoutNotAvailable;
import com.twu.biblioteca.exception.ProductNotFoundException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryService {

    public List<Product> getAvailableProducts(List<Product> products) {
        return products.stream().filter(Product::isAvailable).collect(Collectors.toList());
    }

    public void checkoutProduct(Product product) throws CheckoutNotAvailable {
        if (!product.isAvailable()) {
            throw new CheckoutNotAvailable();
        }
        product.setAvailable(false);
    }

    public void checkinProduct(Product product) throws CheckinNotAvailable {
        if (product.isAvailable()) {
            throw new CheckinNotAvailable();
        }
        product.setAvailable(true);
    }

    public Book findBookByTitle(List<Book> books, String bookTitleQuery) throws ProductNotFoundException {
        return (Book) findProductByTitle(new ArrayList<>(books), bookTitleQuery);
    }

    public Movie findMovieByTitle(List<Movie> movies, String movieTitleQuery) throws ProductNotFoundException {
        return (Movie) findProductByTitle(new ArrayList<>(movies), movieTitleQuery);
    }

    public Product findProductByTitle(List<Product> products, String titleQuery) throws ProductNotFoundException {
        Optional<Product> firstMatchingProduct = products.stream().filter(c -> c.getTitle().equalsIgnoreCase(titleQuery)).findFirst();

        if (!firstMatchingProduct.isPresent()) {
            throw new ProductNotFoundException();
        }

        return firstMatchingProduct.get();
    }

}
