package com.twu.biblioteca.model;

import java.util.ArrayList;

public class Menu {
    private static final String MENU_ITEM_LIST_OF_BOOKS = "[1]List of Books";
    private static final String MENU_ITEM_QUIT_THE_APPLICATION = "[0]Quit the application";
    private static final String MENU_ITEM_CHECKOUT_A_BOOK = "[2]Checkout a Book";
    private static final String MENU_ITEM_RETURN_A_BOOK = "[3]Return a book";
    private static final String MENU_ITEM_LIST_OF_MOVIES = "[4]List of Movies";


    private ArrayList<String> menuOptions = new ArrayList<>();

    public Menu() {
        menuOptions.add(MENU_ITEM_LIST_OF_BOOKS);
        menuOptions.add(MENU_ITEM_CHECKOUT_A_BOOK);
        menuOptions.add(MENU_ITEM_RETURN_A_BOOK);
        menuOptions.add(MENU_ITEM_LIST_OF_MOVIES);
        menuOptions.add(MENU_ITEM_QUIT_THE_APPLICATION);
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

}
