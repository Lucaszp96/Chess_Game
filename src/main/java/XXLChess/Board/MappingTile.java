package XXLChess.Board;

import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import static XXLChess.App.*;


public class MappingTile {
    /**
     * Convert the current mouse coordinates to the tile number corresponding to the chessboard.
     * @param x Mouse X-axis coordinates
     * @param y Mouse Y-axis coordinates
     * @return Return a tile number
     */
    public static int mappingTiles(int x, int y) {
        int mapTileID;
        int a = x/CELLSIZE;
        int b = y/CELLSIZE;
        mapTileID = a + b*BOARD_WIDTH;
        return mapTileID;
    }

    /**
     * Convert the current tile number to the mouse coordinates  corresponding to the chessboard.
     * @param mapTileID The current tile number.
     * @return Return a array stores mouse X-axis and Y-axis coordinates.
     */
    public static int[] getXY(int mapTileID){
        int[] x_y = new int[2];
        x_y[0] = mapTileID/BOARD_WIDTH;
        x_y[1]= mapTileID%BOARD_WIDTH;
        return x_y;
    }

}
