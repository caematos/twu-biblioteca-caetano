package com.twu.biblioteca.service;

import com.twu.biblioteca.db.LibraryDatabase;
import com.twu.biblioteca.exception.CustomerNotFoundException;
import com.twu.biblioteca.exception.InvalidPasswordException;
import com.twu.biblioteca.model.Customer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CustomerServiceTest {
    private CustomerService customerService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        customerService = new CustomerService();
        LibraryDatabase.setCustomers(Collections.singletonList(new Customer("111-1111", "password")));
    }

    @Test
    public void shouldFindCustomerByLogin() throws CustomerNotFoundException {
        String login = "111-1111";

        Customer foundCustomer = customerService.findByLogin(login);

        assertEquals(foundCustomer.getLogin(), login);
    }

    @Test
    public void shouldThrowExceptionWhenLoginWithInvalidUser() throws CustomerNotFoundException, InvalidPasswordException {
        exception.expect(CustomerNotFoundException.class);

        customerService.findByLogin("111-invalid");
    }

}