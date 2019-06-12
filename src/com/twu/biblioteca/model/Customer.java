package com.twu.biblioteca.model;

public class Customer {
    private String login;
    private String password;
    private boolean librarian;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Customer(String login, String password, boolean librarian) {
        this.login = login;
        this.password = password;
        this.librarian = librarian;
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
}
