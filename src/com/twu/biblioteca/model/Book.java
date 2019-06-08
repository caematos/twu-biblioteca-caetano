package com.twu.biblioteca.model;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private int releasedYear;
    private boolean available = true;

    public Book(String title, String author, int releasedYear) {
        this.title = title;
        this.author = author;
        this.releasedYear = releasedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getReleasedYear() == book.getReleasedYear() &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getReleasedYear());
    }

    @Override
    public String toString() {
        return "Book title: " + title + ", Author(s): " + author + ", " + releasedYear;
    }

}
