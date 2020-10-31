package com.amoeba.transformer;

import com.amoeba.domain.Player;
import com.amoeba.domain.Tile;
import com.amoeba.domain.factory.Game;

import java.util.List;

public class GameOutput {

    int victoryCondition;
    int horizontalSize;
    int verticalSize;
    Player activePlayer;
    List<Tile> tileList;
    List<Player> playerList;

    public GameOutput() {}

    public GameOutput(Game game) {
        victoryCondition = game.getVictoryCondition();
        horizontalSize = game.getBoard().length;
        verticalSize = game.getBoard()[0].length;
        activePlayer = game.getActivePlayer();
        tileList = game.getTileList();
        playerList = game.getPlayerList();
    }

    public int getVictoryCondition() {
        return victoryCondition;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
