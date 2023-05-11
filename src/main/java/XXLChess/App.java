package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;

import XXLChess.Board.Board;
import XXLChess.Board.ReadConfig;
import XXLChess.Board.Tile;
import XXLChess.Move.Move;
import XXLChess.Move.MoveToTile;
import XXLChess.Piece.Piece;
import com.jogamp.opengl.GLProfile;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.List;
import java.io.*;
import java.util.*;

import static XXLChess.Board.Board.BoardBuilder.boardCode;

import static XXLChess.Board.Board.initialBoard;
import static XXLChess.Board.MappingTile.*;
import static XXLChess.Board.ReadConfig.*;

public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;
    public  static final  int TILES_NUM = BOARD_WIDTH*BOARD_WIDTH;
    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;
    public static Map<String, PImage> chessTypeImage = new HashMap<>();
    public static int[] x_y;
    public static final int FPS = 60;




    private Board chessBoard;
    public Tile tile;
    public Tile destinationTile;
    public Piece playerMovedPiece;
    public static List<String> chessListConfig;
    public static String configPath;
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
        JSONObject conf = loadJSONObject(new File(this.configPath));
        File chessLayout = new File(conf.getString("layout"));
        chessListConfig = chessConfig(chessLayout);
        chessBoard = initialBoard(chessListConfig);
        // Load images during setup
        PImage b_amazon = loadImage("/src/main/resources/XXLChess/b-amazon.png");
        b_amazon.resize(CELLSIZE, CELLSIZE);
        PImage b_archbishop = loadImage("/src/main/resources/XXLChess/b-archbishop.png");
        b_archbishop.resize(CELLSIZE, CELLSIZE);
        PImage b_bishop = loadImage("/src/main/resources/XXLChess/b-bishop.png");
        b_bishop.resize(CELLSIZE, CELLSIZE);
        PImage b_camel = loadImage("/src/main/resources/XXLChess/b-camel.png");
        b_camel.resize(CELLSIZE, CELLSIZE);
        PImage b_chancellor = loadImage("/src/main/resources/XXLChess/b-chancellor.png");
        b_chancellor.resize(CELLSIZE, CELLSIZE);
        PImage b_king = loadImage("/src/main/resources/XXLChess/b-king.png");
        b_king.resize(CELLSIZE, CELLSIZE);
        PImage b_knight = loadImage("/src/main/resources/XXLChess/b-knight.png");
        b_knight.resize(CELLSIZE, CELLSIZE);
        PImage b_knight_king = loadImage("/src/main/resources/XXLChess/b-knight-king.png");
        b_knight_king.resize(CELLSIZE, CELLSIZE);
        PImage b_pawn = loadImage("/src/main/resources/XXLChess/b-pawn.png");
        b_pawn.resize(CELLSIZE, CELLSIZE);
        PImage b_queen = loadImage("/src/main/resources/XXLChess/b-queen.png");
        b_queen.resize(CELLSIZE, CELLSIZE);
        PImage b_rook = loadImage("/src/main/resources/XXLChess/b-rook.png");
        b_rook.resize(CELLSIZE, CELLSIZE);
        PImage w_amazon = loadImage("/src/main/resources/XXLChess/w-amazon.png");
        w_amazon.resize(CELLSIZE, CELLSIZE);
        PImage w_archbishop = loadImage("/src/main/resources/XXLChess/w-archbishop.png");
        w_archbishop.resize(CELLSIZE, CELLSIZE);
        PImage w_bishop = loadImage("/src/main/resources/XXLChess/w-bishop.png");
        w_bishop.resize(CELLSIZE, CELLSIZE);
        PImage w_camel = loadImage("/src/main/resources/XXLChess/w-camel.png");
        w_camel.resize(CELLSIZE, CELLSIZE);
        PImage w_chancellor = loadImage("/src/main/resources/XXLChess/w-chancellor.png");
        w_chancellor.resize(CELLSIZE, CELLSIZE);
        PImage w_king = loadImage("/src/main/resources/XXLChess/w-king.png");
        w_king.resize(CELLSIZE, CELLSIZE);
        PImage w_knight = loadImage("/src/main/resources/XXLChess/w-knight.png");
        w_knight.resize(CELLSIZE, CELLSIZE);
        PImage w_knight_king = loadImage("/src/main/resources/XXLChess/w-knight-king.png");
        w_knight_king.resize(CELLSIZE, CELLSIZE);
        PImage w_pawn = loadImage("/src/main/resources/XXLChess/w-pawn.png");
        w_pawn.resize(CELLSIZE, CELLSIZE);
        PImage w_queen = loadImage("/src/main/resources/XXLChess/w-queen.png");
        w_queen.resize(CELLSIZE, CELLSIZE);
        PImage w_rook = loadImage("/src/main/resources/XXLChess/w-rook.png");
        w_rook.resize(CELLSIZE, CELLSIZE);
        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);
        // load config
//        JSONObject conf = loadJSONObject(new File(this.configPath));
//        File chessLayout = new File(conf.getString("layout"));
        // Draw board
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_WIDTH; y++) {

                if ((x + y) % 2 == 0) {
                    fill(240, 217, 181); // light yellow
                    rect(y * CELLSIZE, x * CELLSIZE, CELLSIZE, CELLSIZE);
                } else {
                    fill(181, 136, 99); // dark yellow
                    rect(y * CELLSIZE, x * CELLSIZE, CELLSIZE, CELLSIZE);
                }
            }
        }
        // create map
        chessTypeImage.put("P", b_pawn);
        chessTypeImage.put("R", b_rook);
        chessTypeImage.put("N", b_knight);
        chessTypeImage.put("B", b_bishop);
        chessTypeImage.put("H", b_archbishop);
        chessTypeImage.put("C", b_camel);
        chessTypeImage.put("G", b_knight_king);
        chessTypeImage.put("A", b_amazon);
        chessTypeImage.put("K", b_king);
        chessTypeImage.put("E", b_chancellor);
        chessTypeImage.put("Q", b_queen);
        chessTypeImage.put("p", w_pawn);
        chessTypeImage.put("r", w_rook);
        chessTypeImage.put("n", w_knight);
        chessTypeImage.put("b", w_bishop);
        chessTypeImage.put("h", w_archbishop);
        chessTypeImage.put("c", w_camel);
        chessTypeImage.put("g", w_knight_king);
        chessTypeImage.put("a", w_amazon);
        chessTypeImage.put("k", w_king);
        chessTypeImage.put("e", w_chancellor);
        chessTypeImage.put("q", w_queen);
        // print chessList
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
//                System.out.println(chessList[i][j]);
                if (chessTypeImage.get(chessListConfig.get((BOARD_WIDTH * i) + j)) != null) {
                    image(chessTypeImage.get(chessListConfig.get((BOARD_WIDTH * i) + j)), j * CELLSIZE, i * CELLSIZE);
                }
            }
        }

        noLoop();
    }
    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(KeyEvent e){
        String pressed = String.valueOf(e.getKey());

        if (pressed.equals("e") || pressed.equals("E") ){
            System.out.println(pressed);
            exit();
        }
    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mappingTileID = mappingTiles(e.getX(), e.getY());
//        ReadConfig rc = new ReadConfig();
//        Map<String, PImage> chessTypeImageImage = rc.chessTypeImageImage();

        if(e.getButton() == RIGHT){
            tile = null;
            destinationTile = null;
            playerMovedPiece = null;
//            System.out.println("RIGHT" + mouseButton);
        }else if(e.getButton() == LEFT) {
//            System.out.println("LEFT" + mouseButton);
            if (tile == null){ // First selected
                System.out.println("ID:" + mappingTileID);
                tile = chessBoard.getTile(mappingTileID); // Get current tile
                System.out.println("Tile:" + tile.getTileNowLocation());
//                if(tile.getPiece() != null){
                playerMovedPiece = tile.getPiece();
//                System.out.println("Piece:" + playerMovedPiece.getPieceType());
                if (playerMovedPiece == null){
                    tile = null;
                }else {
                    //TODO highlight legalMoves
                    System.out.println("Color:" + playerMovedPiece.pieceColour());
                    System.out.println("Type:" + playerMovedPiece.getPieceColorType());
                    System.out.println("-----------" );
                }
//                }
            }else {
                destinationTile = chessBoard.getTile(mappingTileID); // Get destination tile
                System.out.println("Tile:" + tile.getTileNowLocation());
                System.out.println("destinationTile:" + destinationTile.getTileNowLocation());
                Move move = Move.MoveFactory.createMove(chessBoard, tile.getTileNowLocation(), destinationTile.getTileNowLocation());
                MoveToTile moveToTile = chessBoard.currentPlayer().makeMove(move);
//                System.out.println("nowMoveCheck:" + moveToTile.getMoveCheck().isDone());
//                System.out.println("-----------" );
                if (moveToTile.getMoveCheck().isDone()){
                    chessBoard = moveToTile.getBoard();
//                    chessBoard.getTile(tile.getTileNowLocation()) = Tile.createTile(tile.getTileNowLocation(), null);
                    System.out.println(chessBoard.toString());
                    //TODO Add the move that was mad to the move log
                    //TODO redraw
//                    x_y = getXY(mappingTileID);
//                    System.out.println("x:" + x_y[0]);
//                    System.out.println("y:" + x_y[1]);
//                    System.out.println("now type:" + tile.getPiece().getPieceType());
//                    System.out.println("Start Draw");
//                    clear(image(chessTypeImage.get(tile.getPiece().getPieceColorType())));
                    redraw();
                }
                tile = null;
                destinationTile = null;
                playerMovedPiece = null;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {
        if(mousePressed){
//            int x = mouseX;
//            int y = mouseY;
//            int px = pmouseX;
//            int py = pmouseY;
//            int tileId = mappingTiles(x, y);
//            int pTileId = mappingTiles(px, py);
//            int[] xyp = getXY(tileId);
//            int[] pxyp = getXY(pTileId);
//            image(chessTypeImage.get(chessBoard.getTile(tileId).getPiece().getPieceColorType()),xyp[1] * CELLSIZE, xyp[0] * CELLSIZE);
//            fill(255);
//            rect(pxyp[1] * CELLSIZE, pxyp[0]* CELLSIZE, CELLSIZE, CELLSIZE);
//            System.out.println("x:"+x);
//            System.out.println("y:"+y);
//            System.out.println("px:"+px);
//            System.out.println("py:"+py);

            for (Tile tile : chessBoard.gameBoard) {
                int tID = tile.getTileNowLocation();
//              System.out.println("ID:" + tID + tile.isTileOccupied());
                int[] xy = getXY(tID);
                if (tile.isTileOccupied()) {
//                  System.out.println("Type:" + tile.getPiece().getPieceColorType());
                    image(chessTypeImage.get(tile.getPiece().getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE);
                }else {
                    if ((xy[0] + xy[1]) % 2 == 0){
                        fill(240, 217, 181);
                        rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    }else {
                        fill(181, 136, 99);
                        rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    }
                }
            }
        }
        noLoop();
    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {

        PApplet.main("XXLChess.App");
    }

}
