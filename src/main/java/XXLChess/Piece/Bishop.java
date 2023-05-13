package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    private static final int[] BISHOP_MOVE_CANDIDATE = {-15, -13, 13, 15};
    public Bishop(int pieceLocation, PieceColour piececolour) {
        super("Bishop",pieceLocation, piececolour, true);
    }
    public Bishop(int pieceLocation, PieceColour piececolour, boolean firstMove) {
        super("Bishop",pieceLocation, piececolour, firstMove);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: BISHOP_MOVE_CANDIDATE){
            int destination = this.pieceLocation; //目标格子
            while (Board.isValid(destination)){//目标格子在棋盘上继续找下一个
                if (FirstColum(this.pieceLocation, currentCandidate) ||
                        FirstToLastColum(this.pieceLocation, currentCandidate)||
                        FirstColum(destination, currentCandidate) ||
                        FirstToLastColum(destination, currentCandidate)) {
                    break;
                }
                destination +=currentCandidate;
                if (Board.isValid(destination)){
                    Tile destinationTile = board.getTile(destination);
                    if (!destinationTile.isTileOccupied()){//格子没棋子 执行操作
                        legalMoves.add(new NonAttackMove(board, this, destination));
                    }else {//格子有棋子 执行操作
                        Piece pieceOccupied = destinationTile.getPiece();
                        PieceColour pieceColour = pieceOccupied.pieceColour();
                        if(this.pieceColour != pieceColour){//颜色不一样 执行操作
                            legalMoves.add(new AttactMove(board, this, destination, pieceOccupied));
                        }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }
    @Override
    public Bishop movePiece(Move move) {
        return new Bishop(move.getDestination(), move.getMovePiece().pieceColour());
    }
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -15 || candidatePosition == 13);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -13 || candidatePosition == 15);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "b";
        }else {
            return "B";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "b";
        }else {
            return "B";
        }
    }
}
