package com.amoeba.domain.service;

import com.amoeba.domain.Coordinates;
import com.amoeba.domain.GameState;
import com.amoeba.domain.factory.Game;

public class AmoebaBoardService implements BoardService {

    @Override
    public GameState analyseGameState(Game game) {
        GameState newGameState = GameState.IN_PROGRESS;
        if (checkIfGameIsTie(game.getBoard())) {
            newGameState = GameState.TIE;
        } else if (checkIfGameIsVictory(game)) {
            newGameState = GameState.WON;
        }
        return newGameState;
    }

    private boolean checkIfGameIsTie(String[][] board) {
        for (String[] col : board) {
            for (String tile : col) {
                if (tile == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIfGameIsVictory(Game game) {
        boolean orthogonalVictory = checkOrthogonalTiles(game);
        boolean verticalVictory = checkDiagonalTiles(game);
        return orthogonalVictory || verticalVictory;
    }

    private boolean checkOrthogonalTiles(Game game) {
        Coordinates startDirection = new Coordinates(1, 0);
        return checkTilesInPerpendicularDirections(game, startDirection);
    }

    private boolean checkDiagonalTiles(Game game) {
        Coordinates startDirection = new Coordinates(1, 1);
        return checkTilesInPerpendicularDirections(game, startDirection);
    }

    private boolean checkTilesInPerpendicularDirections(Game game, Coordinates startDirection) {
        boolean victoryInDir1 = checkTilesInDirection(game, startDirection);
        startDirection = startDirection.rotatePlus90();
        boolean victoryInDir2 = checkTilesInDirection(game, startDirection);
        return victoryInDir1 || victoryInDir2;
    }

    private boolean checkTilesInDirection(Game game, Coordinates startDirection) {
        int count = 1;
        int count1 = countSameTiles(game, startDirection);
        startDirection = startDirection.rotate180();
        int count2 = countSameTiles(game, startDirection);
        count += count1 + count2;
        return count >= game.getVictoryCondition();
    }

    private int countSameTiles(Game game, Coordinates direction) {
        Coordinates coord = game.getCurrentCoordinates();
        String playerSign = game.getSign(coord);
        coord = coord.add(direction);
        boolean signIsIdentical = true;
        int count = 0;
        while (!game.coordinatesAreOffBoard(coord) && signIsIdentical) {
            String sign = game.getSign(coord);
            if (playerSign.equals(sign)) {
                count++;
            } else {
                signIsIdentical = false;
            }
            coord = coord.add(direction);
        }
        return count;
    }

}
