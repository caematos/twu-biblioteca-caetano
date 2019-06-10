package com.twu.biblioteca.helper;

import com.twu.biblioteca.model.Book;

import java.util.List;

import static java.util.Arrays.asList;

public class BookHelper {
    private static List<Book> books;

    public static List<Book> getBooksList() {
        if (null == books) {
            return books = asList(new Book().title("Effective Java").author("Joshua Bloch").releasedYear(1998),
                    new Book().title("Clean Code").author("Robert C. Martin").releasedYear(2006),
                    new Book().title("Java Concurrency in Practice").author("Brian Goetz").releasedYear(2000),
                    new Book().title("Head First Design Patterns").author("Eric Freeman & Elisabeth Robson").releasedYear(2002));
        }
        return books;
    }

}
