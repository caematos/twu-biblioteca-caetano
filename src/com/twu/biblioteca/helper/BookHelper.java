package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookHelper {
    public static List<Book> getBooksList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Effective Java", "Joshua Bloch", 1998));
        books.add(new Book("Clean Code", "Robert C. Martin", 2006 ));
        books.add(new Book("Java Concurrency in Practice", "Brian Goetz", 2000));
        books.add(new Book("Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 2002));
        return books;
    }


}
