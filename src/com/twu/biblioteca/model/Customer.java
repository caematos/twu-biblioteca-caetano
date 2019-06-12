package com.twu.biblioteca.model;

public class Customer {
    private String login;
    private String password;
    private boolean librarian;
    private String name;
    private String email;
    private String phone;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Customer(String login, String password, boolean librarian) {
        this.login = login;
        this.password = password;
        this.librarian = librarian;
    }

    public Customer(String login, String password, boolean librarian, String name, String email, String phone) {
        this.login = login;
        this.password = password;
        this.librarian = librarian;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Customer() {

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLibrarian() {
        return librarian;
    }

    public void setLibrarian(boolean librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}

