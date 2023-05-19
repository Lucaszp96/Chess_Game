package XXLChess.Piece;
import XXLChess.Board.*;

import XXLChess.Move.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private static final int[] PAWN_MOVE_CANDIDATE = {13, 14, 15, 28};
    public Pawn(int pieceLocation, PieceColour piececolour) {
        super("Pawn", pieceLocation, piececolour, true);
    }
    public Pawn(int pieceLocation, PieceColour piececolour, boolean firstMove) {
        super("Pawn", pieceLocation, piececolour, firstMove);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: PAWN_MOVE_CANDIDATE) {
           // int destination = this.pieceLocation + (this.pieceColour.getNum()*currentCandidate);
            int destination = 0;
            if (this.pieceColour.white()){
                destination = this.pieceLocation + (this.pieceColour.getUp()*currentCandidate);
            }else {
                destination = this.pieceLocation + (this.pieceColour.getDown()*currentCandidate);
            }
            if (!Board.isValid(destination)){
                continue;
            }
            Tile destinationTile = board.getTile(destination);
            if (!destinationTile.isTileOccupied() && (currentCandidate == 14 )) { //Move 1 space
                // Promotion Move
                if (this.pieceColour.pawnPromotion(destination)){
                    legalMoves.add(new PawnPromotion (new MoveOfPawn(board, this, destination)));
                }else {
                    legalMoves.add(new MoveOfPawn(board, this, destination));
                }
            } else if (!destinationTile.isTileOccupied() && (currentCandidate == 28) && this.firstMove()) { //Move 2 space & check first move
                int upDestination = destination + 14; //white piece
                int downDestination = destination - 14; //black piece
                if (this.pieceColour.white() && !destinationTile.isTileOccupied() && !board.getTile(upDestination).isTileOccupied()){// white piece up direction 1 & 2 tiles are not occupied
                    legalMoves.add(new NonAttackMove(board, this, destination));
                }
                if (this.pieceColour.black() && !destinationTile.isTileOccupied() && !board.getTile(downDestination).isTileOccupied()){
                    legalMoves.add(new NonAttackMove(board, this, destination));
                }
            } else if( currentCandidate == 13 &&  //攻击移动 最后一列白棋，规则失效， 第一列黑棋 规则失效
                        (!(BoardUtils.FIRST_TO_LAST_COLUMN[this.pieceLocation] && this.pieceColour.white()) ||// 最后一列白棋，规则失效
                        !(BoardUtils.FIRST_COLUMN[this.pieceLocation] && this.pieceColour.black()))){ // 第一列黑棋 规则失效
                if (destinationTile.isTileOccupied()){ // if destinationTile is occupied
                    Piece pieceOccupied = destinationTile.getPiece();
                    if (this.pieceColour != pieceOccupied.pieceColour()){ // if current piece and  destinationTile piece are diff , attackMove
                        if (this.pieceColour.pawnPromotion(destination)){
                            legalMoves.add(new PawnPromotion(new MoveOfPawn(board, this, destination)));
                        }else {
                            legalMoves.add(new MoveOfPawn(board, this, destination));
                        }
                    }
                }
            } else if (currentCandidate == 15 && // 最后一列黑棋，规则失效， 第一列白棋 规则失效
                        (!(BoardUtils.FIRST_COLUMN[this.pieceLocation] && this.pieceColour.white()) ||
                        !(BoardUtils.FIRST_TO_LAST_COLUMN[this.pieceLocation] && this.pieceColour.black()))) { // Edge check
                if (destinationTile.isTileOccupied()){
                    Piece pieceOccupied = destinationTile.getPiece();
                    if (this.pieceColour != pieceOccupied.pieceColour()){
                        if (this.pieceColour.pawnPromotion(destination)){
                            legalMoves.add(new PawnPromotion (new MoveOfPawn(board, this, destination)));
                        }else {
                            legalMoves.add(new MoveOfPawn(board, this, destination));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }
    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getDestination(), move.getMovePiece().pieceColour(), false);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "p";
        }else {
            return "P";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "p";
        }else {
            return "P";
        }
    }
    public Piece getPromotionPiece(){
        return new Queen(this.pieceLocation, this.pieceColour, false);
    }

}
