package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Board.BoardUtils;
import XXLChess.Piece.Piece;

public class NonAttackMove extends Move{

    public NonAttackMove(Board board, Piece movePiece, int destination) {
        super(board, movePiece, destination);
    }
    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof NonAttackMove && super.equals(obj);
    }


}
