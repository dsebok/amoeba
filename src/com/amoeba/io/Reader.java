package com.amoeba.io;

import com.amoeba.domain.Response;
import com.amoeba.game.MenuOption;

import java.util.Optional;

public interface Reader {

    Optional<MenuOption> readMenuOption();

    Response readPlayerAction();

    String readGameName();

    Response readCoordinatesAgain();

    boolean gameNameIsApproved(String gameName);

}
