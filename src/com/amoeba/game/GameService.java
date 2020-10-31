package com.amoeba.game;

import com.amoeba.domain.OutputBoard;
import com.amoeba.domain.factory.Game;
import com.amoeba.fileio.FileIO;
import com.amoeba.fileio.FileProcessor;
import com.amoeba.transformer.AmoebaTransformer;
import com.amoeba.transformer.GameOutput;
import com.amoeba.transformer.GameTransformer;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class GameService implements Service {

    private final FileIO fileIO = new FileProcessor();
    private final GameTransformer transformer = new AmoebaTransformer();

    @Override
    public Properties getProperties() {
        return fileIO.getProperties();
    }

    @Override
    public OutputBoard transformToOutputBoard(Game game) {
        String[][] board = game.getBoard();
        return OutputBoard.generate(board);
    }

    @Override
    public Game loadGame(String gameName) {
        String str = fileIO.loadGame(gameName);
        GameOutput output = transformer.transformToGameOutput(str);
        Game game = Game.ofGameOutput(output);
        game.setLoaded();
        return game;
    }

    @Override
    public void saveGame(Game game, String saveName) {
        List<String> gameOutput = transformer.transformToStringList(game.reduceToOutput());
        try {
            fileIO.saveGame(gameOutput, saveName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
