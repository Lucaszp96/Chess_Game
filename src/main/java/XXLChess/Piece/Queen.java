package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    private static final int[] QUEEN_MOVE_CANDIDATE = {-15, -14, -13, -1, 1, 13, 14, 15};
    public Queen(int pieceLocation, PieceColour piececolour) {
        super("Queen", pieceLocation, piececolour);
    }

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: QUEEN_MOVE_CANDIDATE){
            int destination = this.pieceLocation; //目标格子
            while (Board.isValid(destination)){//目标格子在棋盘上继续找下一个
                if (FirstColum(this.pieceLocation, currentCandidate) ||
                        FirstToLastColum(this.pieceLocation, currentCandidate)) {
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
                            legalMoves.add(new AttactMove(board, this, destination,pieceOccupied));
                        }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }
    @Override
    public Queen movePiece(Move move) {
        return new Queen(move.getDestination(), move.getMovePiece().pieceColour());
    }
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -15 || candidatePosition == -1 ||candidatePosition == 13);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -13 || candidatePosition == 1 ||candidatePosition == 15);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "q";
        }else {
            return "Q";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "q";
        }else {
            return "Q";
        }
    }
}
