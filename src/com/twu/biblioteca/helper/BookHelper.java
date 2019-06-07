package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;

public class BookHelper {
    public ArrayList<Book> getDummyBooksList() {
        ArrayList<Book> dummyBookList = new ArrayList<Book>();
        dummyBookList.add(new Book("Effective Java", "Robert C. Martin"));
        dummyBookList.add(new Book("Clean Code", "Joshua Bloch"));
        dummyBookList.add(new Book("Java Concurrency in Practice", "Brian Goetz"));
        return dummyBookList;
    }
}
