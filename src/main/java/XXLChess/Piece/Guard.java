package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Guard extends Piece{
    private static final int[] GUARD_MOVE_CANDIDATE = {-29, -27, -16, -15, -14, -13, -12, -1, 1, 12, 13, 14, 15, 16, 27, 29};
    public Guard(int pieceLocation, PieceColour piececolour) {
        super("Guard", pieceLocation, piececolour, true);
    }
    public Guard(int pieceLocation, PieceColour piececolour, boolean firstMove) {
        super("Guard", pieceLocation, piececolour, firstMove);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: GUARD_MOVE_CANDIDATE){
            int destination = this.pieceLocation + currentCandidate; //目标格子
            if (Board.isValid(destination)){//判断棋子位置是否合法
                if (FirstColum(this.pieceLocation, currentCandidate) ||
                        SecondColumn(this.pieceLocation, currentCandidate) ||
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
    public Guard movePiece(Move move) {
        return new Guard(move.getDestination(), move.getMovePiece().pieceColour());
    }
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -15 || candidatePosition == -1 ||
                candidatePosition == 13);
    }
    private static boolean SecondColumn(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidatePosition == -16 || candidatePosition == 12);
    }
    private static boolean SecondToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -12 || candidatePosition == 16);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -13 || candidatePosition == 1 ||
                candidatePosition == 15);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "g";
        }else {
            return "G";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "g";
        }else {
            return "G";
        }
    }
}
