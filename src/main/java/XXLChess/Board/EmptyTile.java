package XXLChess.Board;

import XXLChess.Piece.Piece;

public class EmptyTile extends Tile {

    public EmptyTile(int boardNum) {
        super(boardNum);
    }

    @Override
    public boolean isBoardOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
