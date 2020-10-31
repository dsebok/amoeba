package com.amoeba.domain.factory;

import com.amoeba.domain.*;
import com.amoeba.domain.service.AmoebaBoardService;
import com.amoeba.domain.service.BoardService;
import com.amoeba.exception.BoardPropertyException;
import com.amoeba.transformer.GameOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private final int victoryCondition;
    private final List<Player> playerList;
    private final String[][] board;
    private final BoardService service = new AmoebaBoardService();
    private List<Tile> tileList = new ArrayList<>();
    private Player activePlayer = Player.X;
    private GameState gamestate = GameState.IN_PROGRESS;
    private Coordinates currentCoordinates;

    private Game(int victoryCondition, int horizontalSize, int verticalSize, List<Player> playerList) {
        this.victoryCondition = victoryCondition;
        board = new String[horizontalSize][verticalSize];
        this.playerList = playerList;
    }

    public static Game createGame(Properties prop) throws BoardPropertyException {
        BoardExceptionHandler.checkBoardProperties(prop);
        int horizontalSize = Integer.parseInt(prop.getProperty(BoardProperty.HORIZONTAL_SIZE.name));
        int verticalSize = Integer.parseInt(prop.getProperty(BoardProperty.VERTICAL_SIZE.name));
        int victoryCondition = Integer.parseInt(prop.getProperty(BoardProperty.VICTORY_CONDITION.name));
        List<Player> playerList = Stream.of(Player.values()).collect(Collectors.toList());
        return new Game(victoryCondition, horizontalSize, verticalSize, playerList);
    }

    public static Game ofGameOutput(GameOutput output) {
        int victoryCondition = output.getVictoryCondition();
        int horizontalSize = output.getHorizontalSize();
        int verticalSize = output.getVerticalSize();
        List<Player> playerList = output.getPlayerList();
        Game game = new Game(victoryCondition, horizontalSize, verticalSize, playerList);
        game.setActivePlayerFromLoadedGame(output.getActivePlayer());
        game.setTilesFromLoadedGame(output.getTileList());
        return game;
    }

    public void insertMove(Coordinates coordinates) {
        tileList.add(new Tile(activePlayer.name(), coordinates));
        int row = coordinates.getX();
        int col = coordinates.getY();
        board[row][col] = activePlayer.name();
        currentCoordinates = coordinates;
    }

    public void nextPlayerTurn() {
        int rank = playerList.indexOf(activePlayer);
        if (++rank == playerList.size()) {
            rank = 0;
        }
        activePlayer = playerList.get(rank);
    }

    public boolean coordinatesAreOffBoardOrOccupied(Coordinates coordinates) {
        if (coordinatesAreOffBoard(coordinates)) {
            return true;
        } else {
            return coordinatesAreOccupied(coordinates);
        }
    }

    public boolean coordinatesAreOffBoard(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        int xMax = board.length;
        int yMax = board[0].length;
        return x < 0 || x >= xMax || y < 0 || y >= yMax;
    }

    private void setActivePlayerFromLoadedGame(Player playerFromLoadedGame) {
        activePlayer = playerFromLoadedGame;
    }

    private void setTilesFromLoadedGame(List<Tile> tileListFromLoadedGame) {
        tileList = tileListFromLoadedGame;
        for (Tile tile : tileList) {
            String sign = tile.getSign();
            Coordinates coord = tile.getCoordinates();
            int x = coord.getX();
            int y = coord.getY();
            board[x][y] = sign;
        }
    }

    private boolean coordinatesAreOccupied(Coordinates coordinates) {
        return getSign(coordinates) != null;
    }

    public void checkGameState() {
        gamestate = service.analyseGameState(this);
    }

    public String getSign(Coordinates coord) {
        return board[coord.getX()][coord.getY()];
    }

    public void setSaved() {
        gamestate = GameState.SAVED;
    }

    public void setLoaded() {
        gamestate = GameState.IN_PROGRESS;
    }

    public boolean isInProgress() {
        return gamestate.equals(GameState.IN_PROGRESS);
    }

    public boolean isTie() {
        return gamestate == GameState.TIE;
    }

    public boolean isWon() {
        return gamestate == GameState.WON;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public String[][] getBoard() {
        return board;
    }

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public int getVictoryCondition() {
        return victoryCondition;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public GameOutput reduceToOutput() {
        return new GameOutput(this);
    }
}
