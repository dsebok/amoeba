package com.amoeba.transformer;

import com.amoeba.domain.Coordinates;
import com.amoeba.domain.Player;
import com.amoeba.domain.Tile;
import com.amoeba.exception.GameFileReadingException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AmoebaTransformer implements GameTransformer {

    private static final String EMPTY = "";
    private static final String LINE_END = "\n";
    private static final String SEPARATOR = ";";
    private static final String LINE_SEPARATOR = "---" + LINE_END;
    private static final String FILE_READING_EXP_MSG = "Nem tartalmaz minden információt a betöltendő játék állomány!";

    @Override
    public List<String> transformToStringList(GameOutput output) {
        List<String> strList = new ArrayList<>();
        String victoryCondition = output.victoryCondition + LINE_END;
        String horizontalSize = output.horizontalSize + SEPARATOR;
        String verticalSize = output.verticalSize + LINE_END;
        String activePlayer = output.activePlayer.getSign() + LINE_END;
        List<String> tileList = getTileStringList(output);
        List<String> playerList = getPlayerStringList(output);
        strList.add(victoryCondition);
        strList.add(LINE_SEPARATOR);
        strList.add(horizontalSize + verticalSize);
        strList.add(LINE_SEPARATOR);
        strList.add(activePlayer);
        strList.add(LINE_SEPARATOR);
        strList.addAll(tileList);
        strList.add(LINE_SEPARATOR);
        strList.addAll(playerList);
        return strList;
    }

    @Override
    public GameOutput transformToGameOutput(String str) {
        String[] outputParts = str.split(LINE_SEPARATOR);
        if (outputParts.length != 5) {
            throw new GameFileReadingException(FILE_READING_EXP_MSG);
        }
        GameOutput output = new GameOutput();
        int victoryCondition = getVictoryCondition(outputParts[0]);
        String[] boardDimensions = outputParts[1].split(SEPARATOR);
        int horizontalSize = getHorizontalSize(boardDimensions);
        int verticalSize = getVerticalSize(boardDimensions);
        Player activePlayer = getActivePlayer(outputParts[2]);
        List<Tile> tileList = getTileList(outputParts[3]);
        List<Player> playerList = getPlayerList(outputParts[4]);
        output.victoryCondition = victoryCondition;
        output.horizontalSize = horizontalSize;
        output.verticalSize = verticalSize;
        output.activePlayer = activePlayer;
        output.tileList = tileList;
        output.playerList = playerList;
        return output;
    }

    private List<String> getTileStringList(GameOutput output) {
        List<String> tileStringList = new ArrayList<>();
        for (Tile tile : output.tileList) {
            tileStringList.add(tile.toString());
        }
        return tileStringList;
    }

    private List<String> getPlayerStringList(GameOutput output) {
        List<String> playerStringList = new ArrayList<>();
        for (Player player : output.playerList) {
            playerStringList.add(player.name() + LINE_END);
        }
        return playerStringList;
    }

    private int getVictoryCondition(String outputPart) {
        return Integer.parseInt(outputPart.replace(LINE_END, EMPTY));
    }

    private int getHorizontalSize(String[] boardDimensions) {
        return Integer.parseInt(boardDimensions[0]);
    }

    private int getVerticalSize(String[] boardDimensions) {
        return Integer.parseInt(boardDimensions[1].replace(LINE_END, EMPTY));
    }

    private Player getActivePlayer(String output) {
        return Player.getPlayerOf(output.replace(LINE_END, EMPTY));
    }

    private List<Tile> getTileList(String outputPart) {
        String[] tileArray = outputPart.split(LINE_END);
        List<Tile> tileList = new ArrayList<>();
        for (String tileString : tileArray) {
            String[] tileParameters = tileString.split(SEPARATOR);
            String sign = tileParameters[0];
            int x = Integer.parseInt(tileParameters[1]);
            int y = Integer.parseInt(tileParameters[2]);
            tileList.add(new Tile(sign, new Coordinates(x, y)));
        }
        return tileList;
    }

    private List<Player> getPlayerList(String outputPart) {
        List<String> playerSigns = List.of(outputPart.split(LINE_END));
        return playerSigns.stream()
                .map(Player::getPlayerOf)
                .collect(Collectors.toList());
    }

}
