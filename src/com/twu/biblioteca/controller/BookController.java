package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookController {
    public ArrayList<Book> getAvailableBooks(ArrayList<Book> bookList) {
      return (ArrayList<Book>) bookList.stream().filter(b -> b.isAvailable()).collect(Collectors.toList());
    }

    public void checkoutBook(Book book) {
        book.setAvailable(false);
    }
}
