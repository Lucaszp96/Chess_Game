package XXLChess.Board;

import static XXLChess.App.BOARD_WIDTH;
import static XXLChess.App.TILES_NUM;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = setColumn(0);
    public static final boolean[] SECOND_COLUMN = setColumn(1);
    public static final boolean[] THIRD_COLUMN = setColumn(2);
    public static final boolean[] THIRD_TO_COLUMN = setColumn(11);
    public static final boolean[] SECOND_TO_LAST_COLUMN = setColumn(12);
    public static final boolean[] FIRST_TO_LAST_COLUMN = setColumn(13);
    public static final boolean[] SEVENTH_RANK = setRank(BOARD_WIDTH * 6);
    public static final boolean[] EIGHTH_RANK = setRank(BOARD_WIDTH * 7);

    /**
     * Set the specified row to true.
     *
     * @param rankNum Specified num.
     * @return An array equal to the number of tiles on the game board, the specified rank is true, and all others are false.
     */
    private static boolean[] setRank(int rankNum) {
        boolean[] rank = new boolean[TILES_NUM];
        do {
            rank[rankNum] = true;
            rankNum++;
        } while (rankNum % BOARD_WIDTH != 0);
        return rank;
    }


    /**
     * Set the specified column to true.
     *
     * @param columnNum Specified num.
     * @return An array equal to the number of tiles on the game board, the specified column is true, and all others are false.
     */
    private static boolean[] setColumn(int columnNum) {//将第1列设为true
        boolean[] column = new boolean[TILES_NUM];
        while (columnNum < TILES_NUM) {
            column[columnNum] = true;
            columnNum += BOARD_WIDTH;
        }
        return column;
    }

    public static boolean isValid(int currentTile) {
        return currentTile >= 0 && currentTile < (BOARD_WIDTH * BOARD_WIDTH);
    }
}
