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
import static org.junit.Assert.assertTrue;

public class LoginServiceTest {

    private LoginService loginService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        loginService = new LoginService();
        LibraryDatabase.setCustomers(Collections.singletonList(new Customer("111-1111", "password")));
    }

    @Test
    public void shouldLoginWithCorrectPassword() throws CustomerNotFoundException, InvalidPasswordException {
        assertTrue(loginService.login("111-1111", "password"));
        assertEquals(CustomerService.getLoggedCustomer().getLogin(), "111-1111");
    }

    @Test
    public void shouldThrowExceptionWhenLoginWithInvalidPassword() throws CustomerNotFoundException, InvalidPasswordException {
        exception.expect(InvalidPasswordException.class);

        loginService.login("111-1111", "wrongpass");
    }

}