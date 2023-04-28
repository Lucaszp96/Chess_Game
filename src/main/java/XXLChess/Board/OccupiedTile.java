package XXLChess.Board;

import XXLChess.Piece.Piece;

public class OccupiedTile extends Tile {
//不调用getPice任何外部类无法调用piceOn变量
    private Piece pieceOn;
    public OccupiedTile(int boardNum, Piece pieceOn) {
        super(boardNum);
        this.pieceOn = pieceOn;
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
