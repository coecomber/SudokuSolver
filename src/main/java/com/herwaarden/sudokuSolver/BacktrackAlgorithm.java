package com.herwaarden.sudokuSolver;

import java.util.stream.IntStream;

public class BacktrackAlgorithm {

    public BacktrackAlgorithm(Sudoku sudoku) {
        solve(sudoku);
        printBoard(sudoku);
    }

    private void printBoard(Sudoku sudoku) {
        for (int row = sudoku.getBOARD_START_INDEX(); row < sudoku.getBOARD_SIZE(); row++) {
            for (int column = sudoku.getBOARD_START_INDEX(); column < sudoku.getBOARD_SIZE(); column++) {
                System.out.print(sudoku.board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private boolean solve(Sudoku sudoku) {
        for (int row = sudoku.getBOARD_START_INDEX(); row < sudoku.getBOARD_SIZE(); row++) {
            for (int column = sudoku.getBOARD_START_INDEX(); column < sudoku.getBOARD_SIZE(); column++) {
                if (sudoku.board[row][column] == sudoku.getNO_VALUE()) {
                    for (int k = sudoku.getMIN_VALUE(); k <= sudoku.getMAX_VALUE(); k++) {
                        sudoku.board[row][column] = k;
                        if (isValid(sudoku.board, row, column, sudoku) && solve(sudoku)) {
                            return true;
                        }
                        sudoku.board[row][column] = sudoku.getNO_VALUE();
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int column, Sudoku sudoku) {
        return rowConstraint(board, row, sudoku) &&
                columnConstraint(board, column, sudoku) &&
                subsectionConstraint(board, row, column, sudoku);
    }

    private boolean subsectionConstraint(int[][] board, int row, int column, Sudoku sudoku) {
        boolean[] constraint = new boolean[sudoku.getBOARD_SIZE()];
        int subsectionRowStart = (row / sudoku.getSUBSECTION_SIZE()) * sudoku.getSUBSECTION_SIZE();
        int subsectionRowEnd = subsectionRowStart + sudoku.getSUBSECTION_SIZE();

        int subsectionColumnStart = (column / sudoku.getSUBSECTION_SIZE()) * sudoku.getSUBSECTION_SIZE();
        int subsectionColumnEnd = subsectionColumnStart + sudoku.getSUBSECTION_SIZE();

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c, sudoku)) return false;
            }
        }
        return true;
    }

    private boolean columnConstraint(int[][] board, int column, Sudoku sudoku) {
        boolean[] constraint = new boolean[sudoku.getBOARD_SIZE()];
        return IntStream.range(sudoku.getBOARD_START_INDEX(), sudoku.getBOARD_SIZE())
                .allMatch(row -> checkConstraint(board, row, constraint, column, sudoku));
    }

    private boolean rowConstraint(int[][] board, int row, Sudoku sudoku) {
        boolean[] constraint = new boolean[sudoku.getBOARD_SIZE()];
        return IntStream.range(sudoku.getBOARD_START_INDEX(), sudoku.getBOARD_SIZE())
                .allMatch(column -> checkConstraint(board, row, constraint, column, sudoku));
    }

    private boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column, Sudoku sudoku) {
        if (board[row][column] != sudoku.getNO_VALUE()) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
