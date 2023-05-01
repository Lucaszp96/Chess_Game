package XXLChess.Board;

import XXLChess.Piece.Piece;

import java.util.HashMap;
import java.util.Map;

// 记录每个棋格
// 抽象类，不能实例化，定义了抽象方法不在这里实现，在子类中实现，因为分为了被占用的棋盘和没被占用的棋盘
public abstract class Tile {
    // Final 可以在定义时指定默认值（后面的代码不能对变量再赋值），也可以不指定默认值，而在后面的代码中对final变量赋初值（仅一次）。
    public static final int BOARD_WIDTH = 14;
    protected final int tileNow;
    private  static Map<Integer,EmptyTile> emptyTileMaps = createEmptyTileMap();
    Tile(int tileNow) {
        this.tileNow = tileNow;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
    private static Map<Integer,EmptyTile> createEmptyTileMap(){
        Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i< BOARD_WIDTH * BOARD_WIDTH; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return emptyTileMap;
    }
    // create tile through a method
    public static Tile createTile(int tileNow, Piece piece){
        if(piece == null){
            return emptyTileMaps.get(tileNow);
        }else {
            return new OccupiedTile(tileNow, piece);
        }
    }
}
