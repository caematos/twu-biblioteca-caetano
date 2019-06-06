package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    public static final String EXPECTED_WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    @Test
    public void shouldReturnExpectedWelcomeMessage() {
        BibliotecaApp app = new BibliotecaApp();

        String actualMessage = app.getWelcomeMessage();
        assertEquals(EXPECTED_WELCOME_MESSAGE, actualMessage);
    }
}
