package com.amoeba.domain.factory;

import com.amoeba.domain.BoardProperty;
import com.amoeba.exception.BoardPropertyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class BoardExceptionHandler {

    private static final String EXP_MESSAGE = "The given board property (name: %s, value: %d) is out of bounds!";

    public static void checkBoardProperties(Properties prop) throws BoardPropertyException {
        Map<BoardProperty, Integer> propertyMap = createPropertyMap(prop);
        checkProperties(propertyMap);
    }

    private static Map<BoardProperty, Integer> createPropertyMap(Properties prop) {
        Map<BoardProperty, Integer> propertyMap = new HashMap<>();
        int horizontalSize = Integer.parseInt(prop.getProperty(BoardProperty.HORIZONTAL_SIZE.name));
        int verticalSize = Integer.parseInt(prop.getProperty(BoardProperty.VERTICAL_SIZE.name));
        int victoryCondition = Integer.parseInt(prop.getProperty(BoardProperty.VICTORY_CONDITION.name));
        propertyMap.put(BoardProperty.HORIZONTAL_SIZE, horizontalSize);
        propertyMap.put(BoardProperty.VERTICAL_SIZE, verticalSize);
        propertyMap.put(BoardProperty.VICTORY_CONDITION, victoryCondition);
        return propertyMap;
    }

    private static void checkProperties(Map<BoardProperty, Integer> propertyMap) throws BoardPropertyException {
        for (Map.Entry<BoardProperty, Integer> entry : propertyMap.entrySet()) {
            checkProperty(entry);
        }
    }

    private static void checkProperty(Map.Entry<BoardProperty, Integer> entry) throws BoardPropertyException {
        BoardProperty property = entry.getKey();
        int value = entry.getValue();
        if (value < property.min || value > property.max) {
            throw new BoardPropertyException(String.format(EXP_MESSAGE, property.name, value));
        }
    }
}
