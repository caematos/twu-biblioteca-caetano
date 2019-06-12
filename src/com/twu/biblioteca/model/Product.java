package com.twu.biblioteca.model;

public abstract class Product {
    public Customer checkoutBy;

    public abstract boolean isAvailable();
    public abstract void setAvailable(boolean available);
    public abstract String getTitle();

    public Customer getCheckoutBy() {
        return checkoutBy;
    }

    public void setCheckoutBy(Customer checkoutBy) {
        this.checkoutBy = checkoutBy;
    }
}
