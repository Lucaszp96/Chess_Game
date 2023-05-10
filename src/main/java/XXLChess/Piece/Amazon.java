package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends Piece{
    private static final int[] KING_MOVE_CANDIDATE = {-15, -14, -13, -1, 1, 13, 14, 15};
    public Amazon(int pieceLocation, PieceColour piececolour) {

        super("Amazon",pieceLocation, piececolour);
    }

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: KING_MOVE_CANDIDATE){
            int destination = this.pieceLocation + currentCandidate; //目标格子
            if (Board.isValid(destination)){//判断棋子位置是否合法
                if (FirstColum(this.pieceLocation, currentCandidate) || FirstToLastColum(this.pieceLocation, currentCandidate)){//如果位于第一列或者最后一列，跳到下个循环
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
    public Amazon movePiece(Move move) {
        return new Amazon(move.getDestination(), move.getMovePiece().pieceColour());
    }

    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -15 || candidatePosition == -1 ||
                candidatePosition == 13);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -13 || candidatePosition == 1 ||
                candidatePosition == 15);
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
