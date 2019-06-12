package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.CustomerNotFoundException;
import com.twu.biblioteca.exception.InvalidPasswordException;
import com.twu.biblioteca.model.Customer;

public class LoginService {

    CustomerService customerService = new CustomerService();

    public boolean login(String login, String password) throws CustomerNotFoundException, InvalidPasswordException {
        Customer customer = customerService.findByLogin(login);
        if (customer.getPassword().equals(password)) {
            customerService.setloggedCustomer(customer);
            return true;
        }
        throw new InvalidPasswordException();
    }
}
