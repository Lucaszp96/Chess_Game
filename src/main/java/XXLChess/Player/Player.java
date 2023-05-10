package XXLChess.Player;

import XXLChess.Board.Board;
import XXLChess.Move.Move;
import XXLChess.Move.MoveCheck;
import XXLChess.Move.MoveToTile;
import XXLChess.Piece.King;
import XXLChess.Piece.Piece;
import XXLChess.Piece.PieceColour;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected Board board;
    protected King playerKing;
    protected List<Move> allLegalMoves;
    protected boolean inCheck;

    public Player(Board board, List<Move> legalMoves, List<Move> enemyMoves) {//oppoentMoves
        this.board = board;
        this.playerKing = fingKing(); // establishKing
        legalMoves.addAll(castling(legalMoves, enemyMoves));
//        this.allLegalMoves = (List<Move>) Iterables.concat(legalMoves, castling(legalMoves, enemyMoves));
        this.allLegalMoves = legalMoves;
        this.inCheck = !underAttacks(this.playerKing.pieceLocation(), enemyMoves).isEmpty();//Do not have inCheck in constructor. it will lead to a Dead Loop
    }
    public King getPlayerKing() {
        return playerKing;
    }
    public List<Move> getLegalMoves() {
        return allLegalMoves;
    }

    // catch all enemyMoves and player's king chess location, if king is under attacked. weather any enemyMoves destination is same as king location
    public static List<Move> underAttacks(int pieceLocation, List<Move> enemyMoves) {
        List<Move> attackMoves = new ArrayList<>();
        for (Move move : enemyMoves){
            if (pieceLocation == move.getDestination()){
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }
    private King fingKing() { // check if the board has a king chess 判断是否合法有没有King
        for (Piece piece : allActivePieces()){
            if (piece.getPieceColorType().equals("K") || piece.getPieceColorType().equals("k")){
                return (King) piece;
            }
        }throw new RuntimeException("Not a valid board!");
    }

    public  boolean isLegalMove(Move move){ // weather a legal move
        return this.allLegalMoves.contains(move);
    }
    public  boolean inCheck(){ // weather check
        return this.inCheck;
    }
    public  boolean checkMate(){ // check mate 将死
        return this.inCheck && !hasEscapeMove();
    }
    private boolean hasEscapeMove() { //
        for (Move move : this.allLegalMoves){
            MoveToTile escape = makeMove(move);
            if (escape.getMoveCheck().isDone()){ // moveCheck movetomove 都有isDown（）
                return true;
            }
        }
        return false;
    }
    // TODO move rules check!!!
    public boolean castled(){ //‘castling’ move 铸造
        return false;
    }
    public boolean staleMate(){ //Stalemate – draw 僵局 没棋子将军，也没地方可以移动,任何移动都会导致check
        return !this.inCheck && !hasEscapeMove();
    }

    public MoveToTile makeMove(Move move){
        if (!isLegalMove(move)){ // Illegal move return current game board
            System.out.println("ILLEGAL_MOVE");
            return new MoveToTile(this.board, move, MoveCheck.ILLEGAL_MOVE);
        }
        Board transitionBoard = move.active();
        List<Move> kingUnderAttacks = underAttacks(transitionBoard.currentPlayer().getEnemy().getPlayerKing().pieceLocation(), transitionBoard.currentPlayer().getLegalMoves());
        //TODO check the rule
//        if(transitionBoard.currentPlayer().getEnemy().inCheck()){ // if king is being attacked return current game board and in check
//            System.out.println("PLAYER_IN_CHECK");
//            return new MoveToTile(this.board, move, MoveCheck.PLAYER_IN_CHECK);
//        }
        System.out.println("DONE");
        return new MoveToTile(transitionBoard, move, MoveCheck.DONE);
    }
    public abstract List<Piece> allActivePieces();
    public abstract PieceColour pieceColour();
    public abstract Player getEnemy();
    public abstract List<Move> castling(List<Move> playerMoves, List<Move> enemyMoves);

}
