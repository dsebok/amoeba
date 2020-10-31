package com.amoeba.formatter;

import com.amoeba.domain.Action;
import com.amoeba.domain.Coordinates;
import com.amoeba.domain.Response;
import com.amoeba.game.MenuOption;

import java.util.Optional;
import java.util.stream.Stream;

public class InputFormatter implements Formatter{

    private static final String SAVE_REGEX = "ment .+";
    private static final String MOVE_REGEX = "[ \t]*[0-9]+[ \t]+[0-9]+[ \t]*";
    private static final String GAME_NAME_REGEX = "[ \t]*[a-zA-Z0-9_]+[ \t]*";

    @Override
    public Optional<MenuOption> interpretMenuInput(String input) {
        return Stream.of(MenuOption.values())
                .filter(option -> option.acceptsThisInput(input))
                .findFirst();
    }

    @Override
    public Response interpretActionInput(String input) {
        Response response = new Response();
        if (input.matches(MOVE_REGEX)) {
            response = transformMoveInput(input);
        } else if (input.matches(SAVE_REGEX)) {
            input = input.trim();
            int firstSpace = input.indexOf(" ");
            String saveName = (input.substring(firstSpace).trim());
            response.setAction(Action.SAVE).setSaveName(saveName);
        }
        return response;
    }

    @Override
    public Response interpretMoveInput(String input) {
        Response response = new Response().setCoordinates(new Coordinates());
        if (input.matches(MOVE_REGEX)) {
            response = transformMoveInput(input);
        }
        return response;
    }

    @Override
    public boolean gameNameIsApproved(String input) {
        return input.matches(GAME_NAME_REGEX);
    }

    private Response transformMoveInput(String input) {
        Response response = new Response();
        input = input.trim();
        int firstSpace = input.indexOf(" ");
        int col = Integer.parseInt(input.substring(0, firstSpace));
        int row = Integer.parseInt(input.substring(firstSpace).trim());
        return response.setAction(Action.MOVE).setCoordinates(new Coordinates(row - 1, col - 1));
    }

}
