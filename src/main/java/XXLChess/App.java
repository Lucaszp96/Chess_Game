package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.core.PFont;
import processing.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.*;

public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;

    public static final int FPS = 60;
	
    public String configPath;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);
        // Load images during setup
        PImage b_amazon = loadImage("/src/main/resources/XXLChess/b-amazon.png");
        PImage b_archbishop = loadImage("/src/main/resources/XXLChess/b-archbishop.png");
        PImage b_bishop = loadImage("/src/main/resources/XXLChess/b-bishop.png");
        PImage b_camel = loadImage("/src/main/resources/XXLChess/b-camel.png");
        PImage b_chancellor = loadImage("/src/main/resources/XXLChess/b-chancellor.png");
        PImage b_king = loadImage("/src/main/resources/XXLChess/b-king.png");
        PImage b_knight = loadImage("/src/main/resources/XXLChess/b-knight.png");
        PImage b_knight_king = loadImage("/src/main/resources/XXLChess/b-knight-king.png");
        PImage b_pawn = loadImage("/src/main/resources/XXLChess/b-pawn.png");
        PImage b_queen = loadImage("/src/main/resources/XXLChess/b-queen.png");
        PImage b_rook = loadImage("/src/main/resources/XXLChess/b-rook.png");
        PImage w_amazon = loadImage("/src/main/resources/XXLChess/w-amazon.png");
        PImage w_archbishop = loadImage("/src/main/resources/XXLChess/w-archbishop.png");
        PImage w_bishop = loadImage("/src/main/resources/XXLChess/w-bishop.png");
        PImage w_camel = loadImage("/src/main/resources/XXLChess/w-camel.png");
        PImage w_chancellor = loadImage("/src/main/resources/XXLChess/w-chancellor.png");
        PImage w_king = loadImage("/src/main/resources/XXLChess/w-king.png");
        PImage w_knight = loadImage("/src/main/resources/XXLChess/w-knight.png");
        PImage w_knight_king = loadImage("/src/main/resources/XXLChess/w-knight-king.png");
        PImage w_pawn = loadImage("/src/main/resources/XXLChess/w-pawn.png");
        PImage w_queen = loadImage("/src/main/resources/XXLChess/w-queen.png");
        PImage w_rook = loadImage("/src/main/resources/XXLChess/w-rook.png");
        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);

        // load config
        JSONObject conf = loadJSONObject(new File(this.configPath));

        // Draw board
//        fill(240,217,181);
//        rect(0,0,CELLSIZE,CELLSIZE);
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_WIDTH; y++) {

                if ((x + y) % 2 == 0) {
                    fill(240, 217, 181);
                    rect(x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);
                } else {
                    fill(181, 136, 99);
                    rect(x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);
                }
            }
        }

        // Read chess file
        File chessFile = new File("level1.txt");
        String[][] chessList = new String[BOARD_WIDTH][BOARD_WIDTH];
        String str;
        char[] c = new char[BOARD_WIDTH];
        int n = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(chessFile));
            while ((str = br.readLine()) != null) {
                if (str.length() != 0) {
                    c = str.toCharArray();
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        chessList[n][j] = String.valueOf(c[j]);
                    }
                } else {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        chessList[n][j] = String.valueOf(' ');
                    }
                }
                n++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // create map
        Map<String, PImage> chessType = new HashMap<>();
        chessType.put("P", b_pawn);
        chessType.put("R", b_rook);
        chessType.put("N", b_knight);
        chessType.put("B", b_bishop);
        chessType.put("H", b_archbishop);
        chessType.put("C", b_camel);
        chessType.put("G", b_knight_king);
        chessType.put("A", b_amazon);
        chessType.put("K", b_king);
        chessType.put("E", b_chancellor);
        chessType.put("Q", b_queen);
        chessType.put("p", w_pawn);
        chessType.put("r", w_rook);
        chessType.put("n", w_knight);
        chessType.put("b", w_bishop);
        chessType.put("h", w_archbishop);
        chessType.put("c", w_camel);
        chessType.put("g", w_knight_king);
        chessType.put("a", w_amazon);
        chessType.put("k", w_king);
        chessType.put("e", w_chancellor);
        chessType.put("q", w_queen);

        // print chesslist
        PImage chessT;
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {

//                System.out.println(chessList[i][j]);
                if (chessType.get(chessList[i][j]) != null) {
                    image(chessType.get(chessList[i][j]), j * CELLSIZE, i * CELLSIZE, CELLSIZE, CELLSIZE);
                }
            }
        }


    }
    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){


    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {




    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {
        PApplet.main("XXLChess.App");
    }

}
