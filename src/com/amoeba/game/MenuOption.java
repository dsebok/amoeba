package com.amoeba.game;

import java.util.List;

public enum MenuOption {
    NEW_GAME(new String[]{"1", "1.", "(1)", "[1]"}),
    LOAD_GAME(new String[]{"2", "2.", "(2)", "[2]"}),
    EXIT(new String[]{"3", "3.", "(3)", "[3]"});

    private final String[] acceptedInputs;

    MenuOption(String[] acceptedInputs) {
        this.acceptedInputs = acceptedInputs;
    }

    public boolean acceptsThisInput(String input) {
        List<String> acceptedList = List.of(acceptedInputs);
        return acceptedList.contains(input);
    }
}
