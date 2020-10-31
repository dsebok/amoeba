package com.amoeba.domain;

public class Tile {

    private final String sign;
    private final Coordinates coordinates;

    public Tile(String sign, Coordinates coordinates) {
        this.sign = sign;
        this.coordinates = coordinates;
    }

    public String getSign() {
        return sign;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return sign + ";" + coordinates.getX() + ";" + coordinates.getY() + "\n";
    }
}
