package com.twu.biblioteca.model;

public abstract class Product {
    public abstract boolean isAvailable();

    public abstract void setAvailable(boolean available);
    public abstract String getTitle();
}
