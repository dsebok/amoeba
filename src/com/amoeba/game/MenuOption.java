package com.amoeba.game;

import java.util.List;

public enum MenuOption {
    NEW_GAME(List.of("1", "1.", "(1)", "[1]")),
    LOAD_GAME(List.of("2", "2.", "(2)", "[2]")),
    EXIT(List.of("3", "3.", "(3)", "[3]"));

    private final List<String> acceptedInputs;

    MenuOption(List<String> acceptedInputs) {
        this.acceptedInputs = acceptedInputs;
    }

    public boolean acceptsThisInput(String input) {
        return acceptedInputs.contains(input);
    }
}
