package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Pawn;
import XXLChess.Piece.Piece;

public class PawnPromotion extends Move{
    Move decoratedMove;
    Pawn promotedPawn;
    public PawnPromotion(Move decoratedMove) {
        super(decoratedMove.getBoard(), decoratedMove.getMovePiece(), decoratedMove.getDestination());
        this.decoratedMove = decoratedMove;
        this.promotedPawn = (Pawn) decoratedMove.getMovePiece();
    }
    @Override
    public Board active(){
        Board pawnMoveBoard = this.decoratedMove.active();
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        for (Piece piece : pawnMoveBoard.currentPlayer().allActivePieces()){
            if (!this.promotedPawn.equals(piece)){
                boardBuilder.setPiece(piece);
            }
        }
        for (Piece piece :  pawnMoveBoard.currentPlayer().getEnemy().allActivePieces()) { // Gain opponent player's all pieces
            boardBuilder.setPiece(piece);
        }
        boardBuilder.setPiece(this.promotedPawn.getPromotionPiece().movePiece(this));
        boardBuilder.setMovePlayer(pawnMoveBoard.currentPlayer().pieceColour());
        return boardBuilder.build();
    }

    @Override
    public boolean underAttack() {
        return this.decoratedMove.underAttack();
    }
    @Override
    public Piece attackPiece() {
        return this.decoratedMove.attackPiece();
    }
    @Override
    public int hashCode(){
        return decoratedMove.hashCode() + (31 * promotedPawn.hashCode());
    }
    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof PawnPromotion && (super.equals(obj));
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
