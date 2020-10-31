package com.amoeba.game;

import com.amoeba.domain.OutputBoard;
import com.amoeba.domain.factory.Game;

import java.util.Properties;

public interface Service {
    
    Properties getProperties();

    Game loadGame(String gameName);

    void saveGame(Game game, String saveName);

    OutputBoard transformToOutputBoard(Game game);

}
