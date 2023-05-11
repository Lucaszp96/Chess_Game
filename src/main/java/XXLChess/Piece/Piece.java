package XXLChess.Piece;

import XXLChess.Board.Board;
import XXLChess.Move.Move;

import java.util.List;

public abstract class Piece {
    protected int pieceLocation;
    protected PieceColour pieceColour;
    protected boolean firstMove;
    protected String pieceType;
    protected int code;

    Piece(String pieceType, int pieceLocation, PieceColour piececolour){
        this.pieceType = pieceType;
        this.pieceLocation = pieceLocation;
        this.pieceColour = piececolour;
        this.code = this.hashCode();
        this.firstMove = true;
    }
    public PieceColour pieceColour(){
        return this.pieceColour;
    }
    public int pieceLocation(){
        return this.pieceLocation;
    }
    // 判断棋子可能移动的位置。
    public abstract List<Move> possibleMoves(Board board);

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean firstMove() {
        return this.firstMove;
    }
    public abstract Piece movePiece(Move move); // Move piece to destination tile
    public String getPieceType() {
        return this.pieceType;
    }

    public abstract String getPieceColorType();
    public String getPieceLabel(PieceColour pieceColour, String pieceType) {
        String label = "";
        if(pieceColour.white()){
            label = String.valueOf(pieceType.charAt(0)).toLowerCase();
        } else if (pieceColour.black()) {
            label = String.valueOf(pieceType.charAt(0)).toUpperCase();
        }
        return label;
    }
    @Override
    public int hashCode() {
        int constant = 31;
        int code = this.getPieceType().hashCode();
        code = constant * code + pieceColour.hashCode();
        code = constant * code + pieceLocation;
        if(firstMove){
            code = constant * code + 1;
        }else {
            code = constant * code + 0;
        }

        return code;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Piece)){
            return false;
        }
        Piece restOfPiece = (Piece) obj;
        return pieceLocation == restOfPiece.pieceLocation() && pieceType == restOfPiece.getPieceType() && pieceColour == restOfPiece.pieceColour() &&
                firstMove == restOfPiece.firstMove();
    }
//    public abstract String pieceType();


}
