package XXLChess.Move;

import XXLChess.Board.Board;

public class MoveToTile {//棋子移动过渡
    private Board board;
    private Move move;
    private MoveCheck moveCheck; //moveStatus

    public MoveToTile(Board board, Move move, MoveCheck moveCheck) {
        this.board = board;
        this.move = move;
        this.moveCheck = moveCheck;
    }

    public MoveCheck getMoveCheck(){
        return this.moveCheck;
    }

    public Board getBoard() {
        return this.board;
    }
}
