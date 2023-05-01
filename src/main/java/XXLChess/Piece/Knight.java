package XXLChess.Piece;

import XXLChess.Board.Board;
import XXLChess.Board.BoardUtils;
import XXLChess.Board.Move;
import XXLChess.Board.Tile;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    private static final int[] MOVE_CANDIDATE = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(int pieceLocation, PieceColour piececolour) {
        super(pieceLocation, piececolour);
    }

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: MOVE_CANDIDATE){
            int destination = this.pieceLocation + currentCandidate; //目标格子
            if (BoardUtils.isValid(destination)){//判断棋子位置是否合法
                if (isFirstColumExclusion(this.pieceLocation, currentCandidate) ||
                        isSecondColumExclusion(this.pieceLocation, currentCandidate) ||
                        isSecondToLastColumExclusion(this.pieceLocation, currentCandidate) ||
                        isFirstToLastColumExclusion(this.pieceLocation, currentCandidate)){//如果有效继续
                    continue;
                }
                Tile destinationTile = board.getTile(destination);
                if (!destinationTile.isTileOccupied()){//格子没棋子 执行操作
                    legalMoves.add(new Move());
                }else {//格子有棋子 执行操作
                    Piece pieceOccupied = destinationTile.getPiece();
                    PieceColour pieceColour = pieceOccupied.getPieceColour();
                    if(this.pieceColour != pieceColour){//颜色不一样 执行操作
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return legalMoves;
    }
    private static boolean isFirstColumExclusion(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -17 || candidatePosition == -10 ||
                candidatePosition == 6 ||candidatePosition == 15);
    }

    private static boolean isSecondColumExclusion(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidatePosition == -10 || candidatePosition == 6);
    }

    private static boolean isSecondToLastColumExclusion(int currentPosition, int candidatePosition){
        return BoardUtils.SECOND_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -6 || candidatePosition == 10);
    }

    private static boolean isFirstToLastColumExclusion(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -15 || candidatePosition == -6 ||
                candidatePosition == 10 || candidatePosition == 17);
    }
}
