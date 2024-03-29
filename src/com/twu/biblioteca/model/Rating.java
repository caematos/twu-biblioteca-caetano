package com.twu.biblioteca.model;

public enum Rating {
    UNRATED("Unrated"),
    ONE_STAR("1"),
    TWO_STARS("2"),
    THREE_STARS("3"),
    FOUR_STARS("4"),
    FIVE_STARS("5"),
    SIX_STARS("6"),
    SEVEN_STARS("7"),
    EIGHT_STARS("8"),
    NINE_STARS("9"),
    TEN_STARS("10");

    private String value;

    Rating(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
