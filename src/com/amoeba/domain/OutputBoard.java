package com.amoeba.domain;

public class OutputBoard {

    private static final String X_STARTER_GRID_ELEMENT = "---";
    private static final String X_GRID_ELEMENT = "+---";
    private static final String Y_GRID_ELEMENT = "|";
    private static final String EMPTY = "   ";
    private final String board;

    private OutputBoard(String board) {
        this.board = board;
    }

    public static OutputBoard generate(String[][] board) {
        int horizontalSize = board.length;
        int verticaSize = board[0].length;
        StringBuilder sb = createHeader(horizontalSize);
        for (int col=0; col < verticaSize; col++) {
            sb.append(createHorizontalGrid(horizontalSize));
            sb.append(createRow(board, horizontalSize, col));
        }
        return new OutputBoard(sb.toString());
    }

    private static StringBuilder createHeader(int horizontalSize) {
        StringBuilder sb = new StringBuilder(EMPTY);
        for (int row=1; row <= horizontalSize; row++) {
            sb.append(Y_GRID_ELEMENT);
            sb.append(parseNumber(row));
        }
        return sb.append("\n");
    }

    private static String createHorizontalGrid(int horizontalSize) {
        StringBuilder sb = new StringBuilder(X_STARTER_GRID_ELEMENT);
        for (int i = 1; i <= horizontalSize; i++) {
            sb.append(X_GRID_ELEMENT);
        }
        return sb.append("\n").toString();
    }

    private static String createRow(String[][] board, int horizontalSize, int col) {
        StringBuilder sb = new StringBuilder(parseNumber(col + 1));
        for (int row=0; row < horizontalSize; row++) {
            sb.append(Y_GRID_ELEMENT);
            sb.append(parseSign(board[row][col]));
        }
        return sb.append("\n").toString();
    }

    private static String parseNumber(int nr) {
        String result = nr + ".";
        if (nr < 10) {
            result = " " + result;
        }
        return result;
    }

    private static String parseSign(String s) {
        String result = EMPTY;
        if (s != null) {
            result = " " + s + " ";
        }
        return result;
    }

    @Override
    public String toString() {
        return board;
    }

}
