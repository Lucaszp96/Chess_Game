package XXLChess.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import static XXLChess.App.BOARD_WIDTH;
import static XXLChess.App.TILES_NUM;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = setColumn(0);
    public static final boolean[] SECOND_COLUMN = setColumn(1);
    public static final boolean[] SECOND_TO_LAST_COLUMN = setColumn(12);
    public static final boolean[] FIRST_TO_LAST_COLUMN = setColumn(13);
//    private BoardUtils() {
//        throw new RuntimeException();
//    }
    private static boolean[] setColumn(int columnNum){//将第1列设为true
        boolean[] column = new boolean[TILES_NUM];
        while (columnNum<TILES_NUM){
            column[columnNum] = true;
            columnNum += BOARD_WIDTH;
        }
        return column;
    }
    public static boolean isValid(int currentTile) {
        return currentTile >= 0 && currentTile < (BOARD_WIDTH * BOARD_WIDTH);
    }

//    public static List<String> chessConfig(File file){
//        String[] chessListConfig = new String[TILES_NUM];
//        String str;
//        char[] c = new char[BOARD_WIDTH];
//        int n = 0;
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            while ((str = br.readLine()) != null) {
//                if (str.length() != 0) {
//                    c = str.toCharArray();
//                    for (int j = 0; j < BOARD_WIDTH; j++) {
//                        chessListConfig[(BOARD_WIDTH*n)+j] = String.valueOf(c[j]);
//                    }
//                } else {
//                    for (int j = 0; j < BOARD_WIDTH; j++) {
//                        chessListConfig[(BOARD_WIDTH*n)+j] = String.valueOf(' ');
//                    }
//                }
//                n++;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return Arrays.asList(chessListConfig);
//    }
    //    private final List<Tile> board;

    //    public Board(List<Tile> board) {
//        this.board = board;
//    }



}
