package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public class NullMove extends Move{
    public NullMove() {
        super(null, null, -1);
    }
    @Override
    public Board active(){
        throw new RuntimeException("Cannot execute null move");
    }
}
