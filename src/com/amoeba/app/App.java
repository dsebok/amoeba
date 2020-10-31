package com.amoeba.app;

import com.amoeba.game.AmoebaMenu;
import com.amoeba.game.Menu;

public class App {

    private final Menu menu = new AmoebaMenu();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        menu.open();
    }
}
