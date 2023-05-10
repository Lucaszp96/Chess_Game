package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public class AttackMoveOfPawn extends AttactMove{
    public AttackMoveOfPawn(Board board, Piece movePiece, int destination, Piece attackedPiece) {
        super(board, movePiece, destination, attackedPiece);
    }
}
