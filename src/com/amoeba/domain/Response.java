package com.amoeba.domain;

import java.util.Optional;

public class Response {

    private Optional<Action> action = Optional.empty();
    private Coordinates coordinates;
    private String saveName;

    public Response setAction(Action action) {
        this.action = Optional.of(action);
        return this;
    }

    public Response setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public Response setSaveName(String saveName) {
        this.saveName = saveName;
        return this;
    }

    public boolean isMove() {
        if (action.isEmpty()) {
            return false;
        } else {
            return action.get().equals(Action.MOVE);
        }
    }

    public boolean isSave() {
        if (action.isEmpty()) {
            return false;
        } else {
            return action.get().equals(Action.SAVE);
        }
    }

    public Optional<Action> getAction() {
        return action;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getSaveName() {
        return saveName;
    }

}
