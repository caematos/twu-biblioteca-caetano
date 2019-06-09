package com.twu.biblioteca;


import com.twu.biblioteca.util.StringUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class BibliotecaAppTest {

    private static final String EXPECTED_WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final int INVALID_USER_CHOICE = 203123131;

    @Test
    public void shouldReturnExpectedWelcomeMessage() {
        String actualMessage = BibliotecaApp.getWelcomeMessage();
        assertEquals(EXPECTED_WELCOME_MESSAGE, actualMessage);
    }

    @Test
    public void shouldReturnAnErrorMessageWhenUserChosesAnInvalidOption() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        BibliotecaApp app = new BibliotecaApp(new PrintStream(outSpy));
        app.executeUserChoice(INVALID_USER_CHOICE);
        assertEquals(StringUtils.cleanStringFromMarkers(outSpy.toString()), "Please, choose a valid option.");
    }

}
