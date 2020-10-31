package com.amoeba.formatter;

import com.amoeba.domain.Response;
import com.amoeba.game.MenuOption;

import java.util.Optional;

public interface Formatter {

    Optional<MenuOption> interpretMenuInput(String input);

    Response interpretActionInput(String input);

    Response interpretMoveInput(String input);

    boolean gameNameIsApproved(String input);

}
