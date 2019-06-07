package com.twu.biblioteca;


import com.twu.biblioteca.helper.BookHelper;
import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private static final String EXPECTED_WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    @Test
    public void shouldReturnExpectedWelcomeMessage() {
        BibliotecaApp app = new BibliotecaApp();

        String actualMessage = app.getWelcomeMessage();
        assertEquals(EXPECTED_WELCOME_MESSAGE, actualMessage);
    }

}
