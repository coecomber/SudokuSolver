package com.herwaarden.sudokuSolver;

import java.util.stream.IntStream;

public class BacktrackAlgorithm {

    public BacktrackAlgorithm(Sudoku sudoku) {

        //Tries to solve and the sudoku on on creation and prints the solution to the console.
        solveSudoku(sudoku);
        printSudoku(sudoku);
    }


    private boolean solveSudoku(Sudoku sudoku){

        //For each row (which is 9 in a sudoku)
        for(int row = 0; row < 9; row++){

            //For every column in the current row
            for(int column = 0; column < 9; column++){

                //If the current value is 0 (which is unassigned)
                if(sudoku.board[row][column] == 0){

                    //Tries to place every number from 1 to 9
                    for(int assignNumber = 1; assignNumber <= 9; assignNumber++){

                        //Try to place the current number in the loop
                        sudoku.board[row][column] = assignNumber;

                        //Check if with the assigned number the current board is valid
                        if (checkIfBoardIsValid(sudoku.board, row, column, sudoku) && solveSudoku(sudoku)) {
                            return true;
                        }
                        sudoku.board[row][column] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIfBoardIsValid(int[][] board, int row, int column, Sudoku sudoku) {
        //returns true if row, column and subgrid are all valid.
        return checkIfRowIsValid(board, row, sudoku) &&
                checkIfColumnIsValid(sudoku, column) &&
                checkIfSubgridIsValid(board, row, column, sudoku);
    }

    private boolean checkIfRowIsValid(int[][] board, int currentRow, Sudoku sudoku) {
        //creates a constraint with 9 arrays all set to false
        boolean[] constraint = new boolean[9];

        return IntStream.range(0, 9)
                .allMatch(column -> checkConstraint(sudoku.board, currentRow, constraint, column));
    }

    private boolean checkIfColumnIsValid(Sudoku sudoku, int currentColumn) {
        //creates a constraint with 9 arrays all set to false
        boolean[] constraint = new boolean[9];

        return IntStream.range(0, 9)
                .allMatch(row -> checkConstraint(sudoku.board, row, constraint, currentColumn));
    }

    private void printSudoku(Sudoku sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                System.out.print(sudoku.board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private boolean checkIfSubgridIsValid(int[][] board, int row, int column, Sudoku sudoku) {
        boolean[] constraint = new boolean[9];
        int subsectionRowStart = (row / 3) * 3;
        int subsectionRowEnd = subsectionRowStart + 3;

        int subsectionColumnStart = (column / 3) * 3;
        int subsectionColumnEnd = subsectionColumnStart + 3;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    private boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != 0) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
