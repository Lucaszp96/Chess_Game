package XXLChess.Board;

import XXLChess.Piece.Piece;

public class OccupiedTile extends Tile {
//不调用getPiece任何外部类无法调用pieceOn变量
    private Piece pieceOn;
    public OccupiedTile(int tileNow, Piece pieceOn) {
        super(tileNow);
        this.pieceOn = pieceOn;
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return pieceOn;
    }
}
