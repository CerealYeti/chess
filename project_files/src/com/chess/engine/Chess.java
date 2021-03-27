package com.chess.engine;

import com.chess.engine.board.Board;

// Driver Class to run the program
public class Chess {

    public static void main(String[] args){
        Board board = Board.createStandardBoard();

        System.out.println(board);
    }
}
