package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public class AttactMove extends Move{
    Piece attackedPiece;
    public AttactMove(Board board, Piece movePiece, int destination, Piece attackedPiece) {
        super(board, movePiece, destination);
        this.attackedPiece = attackedPiece;
    }
    @Override
    public boolean underAttack() {
        return true;
    }
    @Override
    public Piece attackPiece() {
        return this.attackedPiece;
    }
    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof AttactMove)){ // 作用是判断其左边对象是否为其右边类的实例，返回boolean类型的数据
            return false;
        }
        AttactMove restOfAttactMove = (AttactMove) obj;
        return super.equals(obj) && attackPiece().equals(restOfAttactMove.attackPiece());
    }

    public static class PawnAttackMove extends AttactMove{

        public PawnAttackMove(Board board, Piece movePiece, int destination, Piece attackedPiece) {
            super(board, movePiece, destination, attackedPiece);
        }
    }
}
