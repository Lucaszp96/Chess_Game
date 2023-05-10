package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    private static final int[] ROOK_MOVE_CANDIDATE = {-14, -1, 1, 14};
    public Rook(int pieceLocation, PieceColour piececolour) {
        super("Rook", pieceLocation, piececolour);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: ROOK_MOVE_CANDIDATE){
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
    public Rook movePiece(Move move) {
        return new Rook(move.getDestination(), move.getMovePiece().pieceColour());
    }
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -1);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == 1);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "r";
        }else {
            return "R";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "r";
        }else {
            return "R";
        }
    }

}
