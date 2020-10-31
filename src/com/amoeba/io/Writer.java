package com.amoeba.io;

import com.amoeba.domain.OutputBoard;

public interface Writer {

    void greet();

    void farewell();

    void showMainMenu();

    void optionWarning();

    void boardPropertyFailure(String expMsg);

    void drawBoard(OutputBoard game);

    void gameStartMsg();

    void playerTurnMsg(String name);

    void tieMessage();

    void winMessage(String activePlayer);

    void askForGameName();

    void gameContinueMsg();

    void saveMessage();

    void incomprehensibleActionWarning();

    void coordinatesWarning();

    void warnForGameNameNotFound();

}
