package com.amoeba.io;

import com.amoeba.domain.Response;
import com.amoeba.formatter.Formatter;
import com.amoeba.formatter.InputFormatter;
import com.amoeba.game.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class InputReader implements Reader {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Formatter formatter = new InputFormatter();

    @Override
    public Optional<MenuOption> readMenuOption() {
        String input = readLine();
        return formatter.interpretMenuInput(input);
    }

    @Override
    public Response readPlayerAction() {
        String input = readLine();
        return formatter.interpretActionInput(input);
    }

    @Override
    public String readGameName() {
        return readLine();
    }

    @Override
    public Response readCoordinatesAgain() {
        String input = readLine();
        return formatter.interpretMoveInput(input);
    }

    @Override
    public boolean gameNameIsApproved(String gameName) {
        return formatter.gameNameIsApproved(gameName);
    }

    private String readLine() {
        String result = "";
        try {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
