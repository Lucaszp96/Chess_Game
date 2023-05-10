package XXLChess;

import XXLChess.Board.Board;

import static XXLChess.App.chessListConfig;


public class Chess {
    public static void main(String[] args){
        Board board =Board.initialBoard(chessListConfig);
        System.out.println(board);
    }
}
