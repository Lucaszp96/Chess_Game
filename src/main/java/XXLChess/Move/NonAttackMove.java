package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public class NonAttackMove extends Move{

    public NonAttackMove(Board board, Piece movePiece, int destination) {
        super(board, movePiece, destination);
    }

}
