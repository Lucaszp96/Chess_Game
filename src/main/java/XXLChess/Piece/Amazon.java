package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends Piece{
    private static final int[] AMAZON_MOVE_CANDIDATE = {-29, -27, -16, -15, -14, -13, -12, -1, 1, 12, 13, 14, 15, 16, 27, 29};
    public Amazon(int pieceLocation, PieceColour piececolour) {
        super("Amazon",pieceLocation, piececolour, true);
    }
    public Amazon(int pieceLocation, PieceColour piececolour, boolean firstMove) {
        super("Amazon",pieceLocation, piececolour, firstMove);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: AMAZON_MOVE_CANDIDATE){
            if (currentCandidate == -14 || currentCandidate == -1 || currentCandidate== 1 || currentCandidate == 14){
                int destination = this.pieceLocation; // Target tile
                while (Board.isValid(destination)){
                    if (FirstColum(this.pieceLocation, currentCandidate) ||
                            FirstToLastColum(this.pieceLocation, currentCandidate) ||
                            FirstColum(destination, currentCandidate) ||
                            FirstToLastColum(destination, currentCandidate)) {
                        break;
                    }
                    destination +=currentCandidate;
                    if (Board.isValid(destination)){
                        Tile destinationTile = board.getTile(destination);
                        if (!destinationTile.isTileOccupied()){//No occupied next step
                            legalMoves.add(new NonAttackMove(board, this, destination));
                        }else {// Occupied next step
                            Piece pieceOccupied = destinationTile.getPiece();
                            PieceColour pieceColour = pieceOccupied.pieceColour();
                            if(this.pieceColour != pieceColour){// Different color
                                legalMoves.add(new AttactMove(board, this, destination, pieceOccupied));
                            }
                            break;
                        }
                    }
                }
            } else{
                int destination = this.pieceLocation + currentCandidate; // Target tile
                if (Board.isValid(destination)){// Judging whether the chess piece position is legal
                    if (FirstColum(this.pieceLocation, currentCandidate) ||
                            SecondColumn(this.pieceLocation, currentCandidate) ||
                            SecondToLastColum(this.pieceLocation, currentCandidate) ||
                            FirstToLastColum(this.pieceLocation, currentCandidate)){
                        continue;// jump to next loop
                    }
                    Tile destinationTile = board.getTile(destination);
                    if (!destinationTile.isTileOccupied()){//No occupied next step
                        legalMoves.add(new NonAttackMove(board, this, destination));
                    }else {// Occupied next step
                        Piece pieceOccupied = destinationTile.getPiece();
                        PieceColour pieceColour = pieceOccupied.pieceColour();
                        if(this.pieceColour != pieceColour){//Different color
                            legalMoves.add(new AttactMove(board, this, destination,pieceOccupied));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }
    @Override
    public Amazon movePiece(Move move) {
        return new Amazon(move.getDestination(), move.getMovePiece().pieceColour());
    }
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -29 || candidatePosition == -16 ||
                candidatePosition == -15 || candidatePosition == -1 || candidatePosition == 12 ||
                candidatePosition == 13 ||candidatePosition == 27);
    }
    private static boolean SecondColumn(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidatePosition == -16 || candidatePosition == 12);
    }
    private static boolean SecondToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -12 || candidatePosition == 16);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -27 || candidatePosition == -13 ||
                candidatePosition == -12 || candidatePosition == 1 || candidatePosition == 15 || candidatePosition == 16 ||
                candidatePosition == 29);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "a";
        }else {
            return "A";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "a";
        }else {
            return "A";
        }
    }
}
