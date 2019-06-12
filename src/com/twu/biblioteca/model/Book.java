package com.twu.biblioteca.model;

import java.util.Objects;

public class Book extends Product {

    private String title;
    private String author;
    private int releasedYear;
    private boolean available = true;

    public Book(String title, String author, int releasedYear) {
        this.title = title;
        this.author = author;
        this.releasedYear = releasedYear;
    }

    public Book() {

    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public Book author(String author) {
        this.author = author;
        return this;
    }

    public Book releasedYear(int year) {
        this.releasedYear = year;
        return this;
    }

    public Book available(boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
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

    @Override
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
        String checkedOutBy = null == this.checkoutBy ? "" : ", Checked out by: " + this.getCheckoutBy().getLogin();
        return "Book title: " + title + ", Author(s): " + author + ", " + releasedYear + checkedOutBy;
    }


}
