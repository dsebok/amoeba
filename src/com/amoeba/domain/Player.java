package com.amoeba.domain;

import java.util.stream.Stream;

public enum Player {
    X("X"), O("O");

    private final String sign;

    Player(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public static Player getPlayerOf(String sign) {
        return Stream.of(Player.values()).filter(player -> player.sign.equals(sign)).findFirst().get();
    }
}
