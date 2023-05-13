package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public class MoveOfPawn extends Move{
    public MoveOfPawn(Board board, Piece movePiece, int destination) {
        super(board, movePiece, destination);
    }
    @Override
    public boolean equals(Object obj){
        return this == obj || obj instanceof MoveOfPawn && super.equals(obj);
    }
//    @Override
//    public String toString() {
//        return BoardUtils.INSTANCE.getPositionAtCoordinate(this.destinationCoordinate);
//    }
}
