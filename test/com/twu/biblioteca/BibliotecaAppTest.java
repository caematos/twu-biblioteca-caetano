package com.twu.biblioteca;


import com.twu.biblioteca.model.Menu;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BibliotecaAppTest {

    private static final String EXPECTED_WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    public static final int INVALID_USER_CHOICE = 203123131;

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

    @Test
    public void shouldReturnAnErrorMessageWhenUserChosesAnInvalidOption() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        BibliotecaApp app = new BibliotecaApp(new PrintStream(outSpy));
        app.executeUserChoice(INVALID_USER_CHOICE);
        assertEquals(cleanStringFromMarkers(outSpy.toString()), "Please, choose a valid option.");
    }

    private String cleanStringFromMarkers(String string) {
        return string.replaceAll("\\n", "");
    }

}
