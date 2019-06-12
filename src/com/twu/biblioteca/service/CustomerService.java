package com.twu.biblioteca.service;

import com.twu.biblioteca.db.LibraryDatabase;
import com.twu.biblioteca.exception.CustomerNotFoundException;
import com.twu.biblioteca.model.Customer;

import java.util.Optional;

public class CustomerService {
    private static Customer loggedCustomer;

    public Customer findByLogin(String login) throws CustomerNotFoundException {
        Optional<Customer> foundCustomer = LibraryDatabase.getCustomersList().stream().filter(c -> c.getLogin().equalsIgnoreCase(login)).findFirst();

        if (!foundCustomer.isPresent()) {
            throw new CustomerNotFoundException();
        }

        return foundCustomer.get();
    }

    public void setloggedCustomer(Customer customer) {
        loggedCustomer = customer;
    }

    public static Customer getLoggedCustomer() {
        return loggedCustomer;
    }
}
