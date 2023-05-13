package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Camel extends Piece{
    private static final int[] CAMEL_MOVE_CANDIDATE = {-43, -41, -17, -11, 11, 17, 41, 43};
    public Camel(int pieceLocation, PieceColour piececolour) {
        super("Camel",pieceLocation, piececolour, true);
    }
    public Camel(int pieceLocation, PieceColour piececolour, boolean firstMove) {
        super("Camel",pieceLocation, piececolour, firstMove);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: CAMEL_MOVE_CANDIDATE){
            int destination = this.pieceLocation + currentCandidate; //目标格子
            if (Board.isValid(destination)){//判断棋子位置是否合法
                if (FirstColum(this.pieceLocation, currentCandidate) ||
                        SecondColumn(this.pieceLocation, currentCandidate) ||
                        ThirdColumn(this.pieceLocation, currentCandidate) ||
                        ThirdToLastColumn(this.pieceLocation, currentCandidate) ||
                        SecondToLastColum(this.pieceLocation, currentCandidate) ||
                        FirstToLastColum(this.pieceLocation, currentCandidate)){//如果位于第一列或者最后一列，跳到下个循环
                    continue;
                }
                Tile destinationTile = board.getTile(destination);
                if (!destinationTile.isTileOccupied()){//格子没棋子 执行操作
                    legalMoves.add(new NonAttackMove(board, this, destination));
                }else {//格子有棋子 执行操作
                    Piece pieceOccupied = destinationTile.getPiece();
                    PieceColour pieceColour = pieceOccupied.pieceColour();
                    if(this.pieceColour != pieceColour){//颜色不一样 执行操作
                        legalMoves.add(new AttactMove(board, this, destination,pieceOccupied));
                    }
                }
            }
        }
        return legalMoves;
    }
    @Override
    public Camel movePiece(Move move) {
        return new Camel(move.getDestination(), move.getMovePiece().pieceColour());
    }
    // CAMEL_MOVE_CANDIDATE = {-43, -41, -17, -11, 11, 17, 41, 43};
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -43 || candidatePosition == -17 ||
                candidatePosition == 11 || candidatePosition == 41);
    }
    private static boolean SecondColumn(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidatePosition == -17 || candidatePosition == 11);
    }
    private static boolean ThirdColumn(int currentPosition, int candidatePosition){
        return BoardUtils.THIRD_COLUMN[currentPosition] && (candidatePosition == -17 || candidatePosition == 11);
    }
    private static boolean ThirdToLastColumn(int currentPosition, int candidatePosition){
        return BoardUtils.THIRD_TO_COLUMN[currentPosition] && (candidatePosition == -11 || candidatePosition == 17);
    }
    private static boolean SecondToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -11 || candidatePosition == 17);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -41 || candidatePosition == -11 ||
                candidatePosition == 17 || candidatePosition == 43);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "c";
        }else {
            return "C";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "c";
        }else {
            return "C";
        }
    }
}
