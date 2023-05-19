package XXLChess.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import static XXLChess.App.*;

public class ReadConfig{
    /**
     * Read the chessboard configuration file.
     * @param file Configuration file.
     * @return A list stores contents of configuration file.
     */
    public static List<String> chessConfig(File file){
        String[] chessListConfig = new String[TILES_NUM];
        String str;
        char[] c = new char[BOARD_WIDTH];
        int n = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((str = br.readLine()) != null) {
                if (str.length() != 0) {
                    c = str.toCharArray();
                    for (int j = 0; j < BOARD_WIDTH; j++) {
//                        System.out.println(c[j]);
                        chessListConfig[(BOARD_WIDTH*n)+j] = String.valueOf(c[j]);
                    }
                } else {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        chessListConfig[(BOARD_WIDTH*n)+j] = String.valueOf(' ');
                    }
                }
                n++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(chessListConfig);
    }
}
