package com.herwaarden.sudokuSolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class SudokuController {
    Sudoku sudoku = new Sudoku();

    @GetMapping("/sudoku")
    public String getSudoku(@RequestParam(name="board", defaultValue="") String paramString, Model model) {

        String string = Arrays.deepToString(sudoku.board);
        StringBuilder htmlString = new StringBuilder();

        for(char c : string.toCharArray()){
            if(c == '['){
                htmlString.append("<br>");
                htmlString.append(c);
            } else {
                htmlString.append(c);
            }
        }

        StringBuilder debugString = new StringBuilder();
        for(char c : string.toCharArray()){
            if(c == '['){
                debugString.append("\n");
                debugString.append(c);
            } else {
                debugString.append(c);
            }
        }

        System.out.println("----------------------------BEFORE----------------------------");
        System.out.println(debugString);

        System.out.println(" ");
        System.out.println("----------------------------AFTER----------------------------");
        BacktrackAlgorithm backtrackAlgorithm = new BacktrackAlgorithm(sudoku);


        model.addAttribute("board", htmlString);

        return "sudoku";
    }

    //TODO:
    //Make array editable on html page and send it as a model to postmapping
    @PostMapping("/sudoku")
    public Sudoku solveSudoku(@RequestBody String sudoku){
    Sudoku sudokuReturnValue = new Sudoku();

    System.out.println(sudoku);

    return sudokuReturnValue;
    }
}
