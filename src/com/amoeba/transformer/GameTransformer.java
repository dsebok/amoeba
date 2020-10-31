package com.amoeba.transformer;

import java.util.List;

public interface GameTransformer {

    List<String> transformToStringList(GameOutput output);

    GameOutput transformToGameOutput(String str);

}
