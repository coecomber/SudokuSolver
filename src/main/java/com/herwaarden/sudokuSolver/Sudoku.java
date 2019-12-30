package com.herwaarden.sudokuSolver;

public class Sudoku {

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    private final int BOARD_SIZE = 9;
    private final int SUBSECTION_SIZE = 3;
    private final int BOARD_START_INDEX = 0;

    private final int NO_VALUE = 0;
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 9;

    public int[][] board = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };



    public int getSUBSECTION_SIZE() {
        return SUBSECTION_SIZE;
    }

    public int getBOARD_START_INDEX() {
        return BOARD_START_INDEX;
    }

    public int getNO_VALUE() {
        return NO_VALUE;
    }

    public int getMIN_VALUE() {
        return MIN_VALUE;
    }

    public int getMAX_VALUE() {
        return MAX_VALUE;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
