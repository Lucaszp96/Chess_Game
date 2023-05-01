package XXLChess.Piece;

import XXLChess.Board.Board;
import XXLChess.Board.Move;

import java.util.List;

public abstract class Piece {
    protected int pieceLocation;
    protected PieceColour pieceColour;

    Piece(int pieceLocation, PieceColour piececolour){
        this.pieceLocation=pieceLocation;
        this.pieceColour = piececolour;
    }
    public PieceColour getPieceColour(){
        return this.pieceColour;
    }
    // 判断棋子可能移动的位置。
    public abstract List<Move> possibleMoves(Board board);
}
