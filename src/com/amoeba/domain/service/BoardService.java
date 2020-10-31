package com.amoeba.domain.service;

import com.amoeba.domain.GameState;
import com.amoeba.domain.factory.Game;

public interface BoardService {

    GameState analyseGameState(Game game);

}
