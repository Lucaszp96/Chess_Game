package XXLChess.Board;

import XXLChess.Piece.Piece;

public class EmptyTile extends Tile {

    public EmptyTile(int tileNow) {
        super(tileNow);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
