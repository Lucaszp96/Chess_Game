package XXLChess.Board;

import static XXLChess.App.BOARD_WIDTH;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SECOND_TO_LAST_COLUMN = null;
    public static final boolean[] FIRST_TO_LAST_COLUMN = null;
//    private BoardUtils() {
//        throw new RuntimeException();
//    }


    public static boolean isValid(int currentTile) {
        return currentTile >= 0 && currentTile < (BOARD_WIDTH * BOARD_WIDTH);
    }
}
