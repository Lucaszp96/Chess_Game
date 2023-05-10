package XXLChess.Board;

import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import static XXLChess.App.*;


public class MappingTile {
//    public static Board initialBoard = Board.initialBoard(chessListConfig);
    public static int mappingTiles(int a, int b) {
        int mapTileID;
        int x = a/CELLSIZE;
        int y = b/CELLSIZE;
        mapTileID = x + y*BOARD_WIDTH;
        return mapTileID;
    }
    public static int[] getXY(int mapTileID){
        int[] x_y = new int[2];
        x_y[0] = mapTileID/BOARD_WIDTH;
        x_y[1]= mapTileID%BOARD_WIDTH;
        return x_y;
    }

}
