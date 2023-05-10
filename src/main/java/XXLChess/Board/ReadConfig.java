package XXLChess.Board;

import processing.core.PImage;
import processing.data.JSONObject;
import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static XXLChess.App.*;
import static processing.core.PApplet.loadJSONObject;





public class ReadConfig{
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
