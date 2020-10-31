package com.amoeba.io;

import com.amoeba.domain.OutputBoard;

import java.util.concurrent.TimeUnit;

public class OutputWriter implements Writer {

    public static final int GREETING_TIMEOUT = 2;
    private static final String OPTION_WARNING = "Rosszul adtad meg a menüpontot, kérlek add meg újra! (1, 2, 3)";
    private static final String FAREWELL_MSG = "Viszlát! (Édesapám, maga felé dől a fa!)";
    private static final String BOARD_EXCEPTION_FAREWELL_MSG = "Korrigáld a hibás paramétert, majd indítsd újra a játékot!";
    private static final String GAME_START_MSG = "A játék elkezdődött!";
    private static final String TIE_MSG = "Vége a játéknak! Az eredmény döntetlen!";
    private static final String WIN_MSG = "Vége a játéknak! Az %s játékos győzedelmeskedett!\n";
    private static final String GAME_NAME_REQUEST = "Kérlek, add meg a betölteni kívánt játék nevét (kiterjesztés nélkül)!";
    private static final String GAME_CONTINUE_MSG = "A játék folytatódik az elmentett állapotból!";
    private static final String CURRENT_GAME_STATE_MSG = "A játék jelenlegi állása:";
    private static final String NEXT_PLAYER_TURN_MSG = "A következő játékos: %s\n";
    private static final String INCOMP_ACTION_MSG = "Nem volt értelmezhető az általad megadott utasítás! Add meg újra!";
    private static final String SAVE_MSG = "A játék sikeresen el lett mentve!";
    private static final String GAMENAME_NOT_FOUND_WARNING = "A megadott játék nem található a \"saves\" mappában! Adj meg egy létező játéknevet.";
    private static final String TURN_OPTIONS_MSG = "Add meg a következő lépésed koordinátáját! (Minta: 13 4)\n" +
            "Vagy mentsd el a játékot \"ment játéknév\" paranccsal. A játéknevet kiterjesztés nélkül add meg!" +
            "(A játéknévhez csak ékezet nélküli karaktert, számot vagy alávonást használj.)";
    private static final String WRONG_COORDINATES_MSG = "A megadott koordináták kívül esnek a játéktábla határán vagy már elfoglalt mezőre mutatnak.\n" +
            "Add meg őket újra!";
    private static final String MENU_OPTIONS = "Főmenü - írd be a kívánt menüpont számát, majd nyomd meg az [ENTER] gombot:\n" +
            "1. Új játék indítása\n" +
            "2. Játék betöltése\n" +
            "3. Kilépés";
    private static final String[] GREETING_MSG = new String[5];

    static {
        GREETING_MSG[0] = "Üdv a világ legjobb amőba játékában!";
        GREETING_MSG[1] = "Készen állsz a harcra?";
        GREETING_MSG[2] = "Mármint... még nincs AI...";
        GREETING_MSG[3] = "Szóval legalább ketten kelletek...";
        GREETING_MSG[4] = "Készen álltok a harcra?:))\n";
    }

    @Override
    public void greet() {
        for (String greetingLine : GREETING_MSG) {
            System.out.println(greetingLine);
            try {
                TimeUnit.SECONDS.sleep(GREETING_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void farewell() {
        System.out.println(FAREWELL_MSG);
    }

    @Override
    public void showMainMenu() {
        System.out.println(MENU_OPTIONS);
    }

    @Override
    public void drawBoard(OutputBoard board) {
        System.out.println(CURRENT_GAME_STATE_MSG);
        System.out.println(board);
    }

    @Override
    public void gameStartMsg() {
        System.out.println(GAME_START_MSG);
    }

    @Override
    public void playerTurnMsg(String name) {
        System.out.printf((NEXT_PLAYER_TURN_MSG), name);
        System.out.println(TURN_OPTIONS_MSG);
    }

    @Override
    public void tieMessage() {
        System.out.println(TIE_MSG);
    }

    @Override
    public void winMessage(String activePlayer) {
        System.out.printf((WIN_MSG), activePlayer);
    }

    @Override
    public void askForGameName() { System.out.println(GAME_NAME_REQUEST); }

    @Override
    public void gameContinueMsg() {
        System.out.println(GAME_CONTINUE_MSG);
    }

    @Override
    public void saveMessage() {
        System.out.println(SAVE_MSG);
    }

    @Override
    public void incomprehensibleActionWarning() {
        System.out.println(INCOMP_ACTION_MSG);
        System.out.println(TURN_OPTIONS_MSG);
    }

    @Override
    public void coordinatesWarning() {
        System.out.println(WRONG_COORDINATES_MSG);
    }

    @Override
    public void warnForGameNameNotFound() { System.out.println(GAMENAME_NOT_FOUND_WARNING); }

    @Override
    public void optionWarning() {
        System.out.println(OPTION_WARNING);
    }

    @Override
    public void boardPropertyFailure(String expMsg) {
        System.out.println(expMsg);
        System.out.println(BOARD_EXCEPTION_FAREWELL_MSG);
    }
}
