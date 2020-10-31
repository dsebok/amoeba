package com.amoeba.domain;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates() {
        this(0,0);
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates rotatePlus90() {
        return new Coordinates(-y, x);
    }

    public Coordinates rotate180() {
        return new Coordinates(-x, -y);
    }

    public Coordinates add(Coordinates other) {
        int otherX = other.getX();
        int otherY = other.getY();
        return new Coordinates(x + otherX, y + otherY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
