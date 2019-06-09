package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;

public class BookHelper {
    public ArrayList<Book> getDummyBooksList() {
        ArrayList<Book> dummyBookList = new ArrayList<>();
        dummyBookList.add(new Book("Effective Java", "Joshua Bloch", 1998));
        dummyBookList.add(new Book("Clean Code", "Robert C. Martin", 2006 ));
        dummyBookList.add(new Book("Java Concurrency in Practice", "Brian Goetz", 2000));
        dummyBookList.add(new Book("Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 2002));
        return dummyBookList;
    }
}
