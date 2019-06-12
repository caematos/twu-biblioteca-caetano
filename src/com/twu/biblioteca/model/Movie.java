package com.twu.biblioteca.model;

import java.util.Objects;

public class Movie extends Product {
    private String title;
    private String director;
    private int year;
    private Rating rating;
    private boolean available = true;

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public Movie director(String director) {
        this.director = director;
        return this;
    }

    public Movie year(int year) {
        this.year = year;
        return this;
    }

    public Movie available(boolean available) {
        this.available = available;
        return this;
    }

    public Movie rating (Rating rating) {
        this.rating = rating;
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        String checkedOutBy = null == this.checkoutBy ? "" : ", Checked out by: " + this.getCheckoutBy().getLogin();
        return "Movie title: " + title + ", Director: " + director + ", Year: " + year + ", Rating: " + rating.toString() + checkedOutBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getYear() == movie.getYear() &&
                isAvailable() == movie.isAvailable() &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getDirector(), movie.getDirector()) &&
                getRating() == movie.getRating();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDirector(), getYear(), getRating(), isAvailable());
    }
}
