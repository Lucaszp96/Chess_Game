package XXLChess.Board;

import XXLChess.Piece.Piece;

import java.util.HashMap;
import java.util.Map;

import static XXLChess.App.TILES_NUM;

// 记录每个棋格
// 抽象类，不能实例化，定义了抽象方法不在这里实现，在子类中实现，因为分为了被占用的棋盘和没被占用的棋盘
public abstract class Tile {
    //Final can specify a default value at the time of definition (the following code cannot assign a value to the variable),
    // or it can not specify a default value, and assign a value to the final variable in the following code (only once).
    public static final int BOARD_WIDTH = 14;
    protected final int tileNowLocation;
    private  static Map<Integer,EmptyTile> emptyTileMaps = createEmptyTileMap();
    Tile(int tileNowLocation) {
        this.tileNowLocation = tileNowLocation;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
    public int getTileNowLocation(){
        return this.tileNowLocation;
    }
    private static Map<Integer,EmptyTile> createEmptyTileMap(){
        Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i< TILES_NUM; i++){
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
