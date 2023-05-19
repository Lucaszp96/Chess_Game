package XXLChess.Piece;

import XXLChess.Board.*;
import XXLChess.Move.AttactMove;
import XXLChess.Move.CastleMove;
import XXLChess.Move.Move;
import XXLChess.Move.NonAttackMove;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final int[] KING_MOVE_CANDIDATE = {-15, -14, -13, -1, -2, 2, 1, 13, 14, 15};
    public King(int pieceLocation, PieceColour piececolour) {
        super("King", pieceLocation, piececolour, true);
    }
    public King(int pieceLocation, PieceColour piececolour, boolean firstMove) {
        super("King", pieceLocation, piececolour, firstMove);
    }
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (int currentCandidate: KING_MOVE_CANDIDATE){
            int destination = this.pieceLocation + currentCandidate; //目标格子
            if (Board.isValid(destination) && Board.isValid(destination-1) && Board.isValid(destination+1)){//判断棋子位置是否合法
                if (FirstColum(this.pieceLocation, currentCandidate) || FirstToLastColum(this.pieceLocation, currentCandidate)){//如果位于第一列或者最后一列，跳到下个循环
                    continue;
                }
                Tile destinationTile = board.getTile(destination);
                if(currentCandidate == 2 || currentCandidate == -2){//castling
                    Tile castlingRight = board.getTile(destination-1);
                    Tile castlingLeft = board.getTile(destination+1);
                    if (this.firstMove && !destinationTile.isTileOccupied()){
                        if (this.pieceColour.equals(PieceColour.WHITE) && currentCandidate == 2 && !castlingRight.isTileOccupied() && this.firstMove ){
                            Tile rookCurrentTile = board.getTile(195);
                            if (rookCurrentTile.getPiece().getPieceColorType().equals("r")){
                                legalMoves.add(new CastleMove(board, (Piece) this, destination, (Rook) rookCurrentTile.getPiece(), 195, 190));
                            }
                        } else if (this.pieceColour.equals(PieceColour.WHITE) && currentCandidate == -2 && !castlingLeft.isTileOccupied() && this.firstMove) {
                            Tile rookCurrentTile = board.getTile(182);
                            if (rookCurrentTile.getPiece().getPieceColorType().equals("r")){
                                legalMoves.add(new CastleMove(board, (Piece) this, destination, (Rook) rookCurrentTile.getPiece(), 182, 188));
                            }
                        } else if (this.pieceColour.equals(PieceColour.BLACK) && currentCandidate == 2 && !castlingRight.isTileOccupied() && this.firstMove) {
                            Tile rookCurrentTile = board.getTile(13);
                            if (rookCurrentTile.getPiece().getPieceColorType().equals("R")){
                                legalMoves.add(new CastleMove(board, (Piece) this, destination, (Rook) rookCurrentTile.getPiece(), 13, 8));
                            }
                        }else if (this.pieceColour.equals(PieceColour.BLACK) && currentCandidate == -2 && !castlingLeft.isTileOccupied() && this.firstMove) {
                            Tile rookCurrentTile = board.getTile(0);
                            if (rookCurrentTile.getPiece().getPieceColorType().equals("R")){
                                legalMoves.add(new CastleMove(board, (Piece) this, destination, (Rook) rookCurrentTile.getPiece(), 0, 6));
                            }
                        }
                    }
                }else {
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
        }

        return legalMoves;
    }
    @Override
    public King movePiece(Move move) {
        return new King(move.getDestination(), move.getMovePiece().pieceColour(), false);
    }
    private static boolean FirstColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == -15 || candidatePosition == -1 || candidatePosition == -2 ||
                candidatePosition == 13);
    }
    private static boolean FirstToLastColum(int currentPosition, int candidatePosition){
        return BoardUtils.FIRST_TO_LAST_COLUMN[currentPosition] && (candidatePosition == -13 || candidatePosition == 1 || candidatePosition == 2 ||
                candidatePosition == 15);
    }
    @Override
    public String toString(){
        if (this.pieceColour.white()){
            return "k";
        }else {
            return "K";
        }
    }
    @Override
    public String getPieceColorType(){
        if (this.pieceColour.white()){
            return "k";
        }else {
            return "K";
        }
    }
}
