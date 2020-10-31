package com.amoeba.game;

import com.amoeba.io.InputReader;
import com.amoeba.io.OutputWriter;
import com.amoeba.io.Reader;
import com.amoeba.io.Writer;

import java.util.Optional;

public class AmoebaMenu implements Menu {

    private final Reader reader = new InputReader();
    private final Writer writer = new OutputWriter();
    private final Game game = new AmoebaGame();

    @Override
    public void open() {
        writer.greet();
        showMainMenu();
    }

    private void showMainMenu() {
        writer.showMainMenu();
        MenuOption option = getMenuOption();
        processChosenOption(option);
    }

    private MenuOption getMenuOption() {
        Optional<MenuOption> option = reader.readMenuOption();
        while (option.isEmpty()) {
            writer.optionWarning();
            option = reader.readMenuOption();
        }
        return option.get();
    }

    private void processChosenOption(MenuOption option) {
        boolean backToMainMenu = true;
        switch (option) {
            case NEW_GAME -> game.startNewGame();
            case LOAD_GAME -> game.loadGame();
            case EXIT -> {
                writer.farewell();
                backToMainMenu = false;
            }
        }
        if (backToMainMenu) {
            showMainMenu();
        }
    }

}
