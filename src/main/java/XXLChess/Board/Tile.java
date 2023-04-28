package XXLChess.Board;

import XXLChess.Piece.Piece;
// 记录每个棋格
// 抽象类，不能实例化，定义了抽象方法不在这里实现，在子类中实现，因为分为了被占用的棋盘和没被占用的棋盘
public abstract class Tile {
    // Final 可以在定义时指定默认值（后面的代码不能对变量再赋值），也可以不指定默认值，而在后面的代码中对final变量赋初值（仅一次）。
    protected final int boardNum;
    public Tile(int boardNum) {
        this.boardNum = boardNum;
    }

    public abstract boolean isBoardOccupied();
    public abstract Piece getPiece();


}
