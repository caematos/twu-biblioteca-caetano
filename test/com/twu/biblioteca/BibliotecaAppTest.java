package com.twu.biblioteca;


import com.twu.biblioteca.model.Menu;
import org.junit.Test;

import static org.junit.Assert.*;


public class BibliotecaAppTest {

    private static final String EXPECTED_WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    @Test
    public void shouldReturnExpectedWelcomeMessage() {
        String actualMessage = BibliotecaApp.getWelcomeMessage();
        assertEquals(EXPECTED_WELCOME_MESSAGE, actualMessage);
    }

    @Test
    public void shouldReturnListOfBooksOptionInMenu() {
        Menu menu = new Menu();
        assertTrue(menu.getMenuOptions().contains("[1] - List of Books"));
    }

}
