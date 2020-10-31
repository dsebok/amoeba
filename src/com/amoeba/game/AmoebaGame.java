package com.amoeba.game;

import com.amoeba.domain.Coordinates;
import com.amoeba.domain.OutputBoard;
import com.amoeba.domain.Response;
import com.amoeba.domain.factory.Game;
import com.amoeba.exception.BoardPropertyException;
import com.amoeba.io.InputReader;
import com.amoeba.io.OutputWriter;
import com.amoeba.io.Reader;
import com.amoeba.io.Writer;

import java.util.Properties;

public class AmoebaGame implements com.amoeba.game.Game {

    private final Reader reader = new InputReader();
    private final Writer writer = new OutputWriter();
    private final Service service = new GameService();

    @Override
    public void startNewGame() {
        Properties prop = service.getProperties();
        Game game = createBoard(prop);
        writer.gameStartMsg();
        play(game);
    }

    @Override
    public void loadGame() {
        writer.askForGameName();
        String gameName = reader.readGameName();
        while (! reader.gameNameIsApproved(gameName)) {
            writer.warnForGameNameNotFound();
            gameName = reader.readGameName();
        }
        //- betöltésnél kiterjesztés nélküli filenév olvasása
        //- hiányzó vagy kiterjesztéssel megadott esetben kérje be újra
        Game game = service.loadGame(gameName);//TODO
        writer.gameContinueMsg();
        play(game);
    }

    private Game createBoard(Properties prop) {
        Game game = null;
        try {
            game = Game.createGame(prop);
        } catch (BoardPropertyException exp) {
            writer.boardPropertyFailure(exp.getMessage());
            System.exit(0);
        }
        return game;
    }

    private void play(Game game) {
        while (game.isInProgress()) {
            drawBoard(game);
            String playerName = game.getActivePlayer().name();
            writer.playerTurnMsg(playerName);
            Response response = readPlayerAction();
            if (response.isMove()) {
                makeTheMove(game, response);
                checkGameState(game, playerName);
            }
            if (response.isSave()) {
                String saveName = response.getSaveName();
                //TODO
                //csak a-zA-Z0-9_
                //- mentésnél ellenőrzés felülírásra, nem válasz esetén új filenév bekérése
                service.saveGame(game, saveName);
                game.setSaved();
                writer.saveMessage();
            }
        }
    }

    private void drawBoard(Game game) {
        OutputBoard board = service.transformToOutputBoard(game);
        writer.drawBoard(board);
    }

    private Response readPlayerAction() {
        Response response = reader.readPlayerAction();
        while (response.getAction().isEmpty()) {
            writer.incomprehensibleActionWarning();
            response = reader.readPlayerAction();
        }
        return response;
    }

    private void makeTheMove(Game game, Response response) {
        Coordinates currentCoordinates = response.getCoordinates();
        while (game.coordinatesAreOffBoardOrOccupied(currentCoordinates)) {
            writer.coordinatesWarning();
            currentCoordinates = reader.readCoordinatesAgain().getCoordinates();
        }
        game.insertMove(currentCoordinates);
        game.nextPlayerTurn();
    }

    private void checkGameState(Game game, String playerName) {
        game.checkGameState();
        if (game.isTie()) {
            drawBoard(game);
            writer.tieMessage();
        } else if (game.isWon()) {
            drawBoard(game);
            writer.winMessage(playerName);
        }
    }

}
