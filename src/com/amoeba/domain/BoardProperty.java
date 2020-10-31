package com.amoeba.domain;

public enum BoardProperty {
    HORIZONTAL_SIZE(Constants.HORIZONTAL_SIZE_MAX, Constants.HORIZONTAL_SIZE_MIN, Constants.HORIZONTAL_SIZE_PROP_NAME),
    VERTICAL_SIZE(Constants.VERTICAL_SIZE_MAX, Constants.VERTICAL_SIZE_MIN, Constants.VERTICAL_SIZE_PROP_NAME),
    VICTORY_CONDITION(Constants.VICTORY_CONDITION_MAX, Constants.VICTORY_CONDITION_MIN, Constants.VICTORY_CONDITION_PROP_NAME);

    public final int max;
    public final int min;
    public final String name;

    BoardProperty(int max, int min, String name) {
        this.max = max;
        this.min = min;
        this.name = name;
    }

    private static class Constants {
        private static final int HORIZONTAL_SIZE_MIN = 5;
        private static final int VERTICAL_SIZE_MIN = 5;
        private static final int VICTORY_CONDITION_MIN = 3;
        private static final int HORIZONTAL_SIZE_MAX = 99;
        private static final int VERTICAL_SIZE_MAX = 99;
        private static final int VICTORY_CONDITION_MAX = 99;
        private static final String HORIZONTAL_SIZE_PROP_NAME = "horizontal_size";
        private static final String VERTICAL_SIZE_PROP_NAME = "vertical_size";
        private static final String VICTORY_CONDITION_PROP_NAME = "victory_condition";
    }
}
