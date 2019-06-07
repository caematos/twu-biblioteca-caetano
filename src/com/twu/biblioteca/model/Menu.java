package com.twu.biblioteca.model;

import java.util.ArrayList;

public class Menu {
    public static final String MENU_ITEM_LIST_OF_BOOKS = "[1] - List of Books";
    private ArrayList<String> menuOptions = new ArrayList<>();


    public Menu() {
        menuOptions.add(MENU_ITEM_LIST_OF_BOOKS);
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(ArrayList<String> menuOptions) {
        this.menuOptions = menuOptions;
    }
}
