package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;

import XXLChess.Board.Board;
import XXLChess.Board.Tile;
import XXLChess.Move.Move;
import XXLChess.Move.MoveToTile;
import XXLChess.Piece.Piece;
import XXLChess.Piece.PieceColour;
import XXLChess.Sounds.Sound;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static XXLChess.Board.Board.initialBoard;
import static XXLChess.Board.HighlightLegalMoves.pieceLegalMoves;
import static XXLChess.Board.MappingTile.getXY;
import static XXLChess.Board.MappingTile.mappingTiles;
import static XXLChess.Board.ReadConfig.chessConfig;
import static XXLChess.Player.Timer.showTimer;

public class App<global> extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;
    public static final int TILES_NUM = BOARD_WIDTH * BOARD_WIDTH;
    public static int WIDTH = CELLSIZE * BOARD_WIDTH + SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH * CELLSIZE;
    public static Map<String, PImage> chessTypeImage = new HashMap<>();
    public static int[] x_y;
    public static final int FPS = 60;


    private Board chessBoard;
    public Tile tile;
    public Tile destinationTile;
    public Piece playerMovedPiece;
    public static List<String> chessListConfig;
    public static String configPath;
    public int whitePlayerTimer = 180;
    public int blackPlayerTimer = 180;
    public int frame;
    public int s;
    public boolean isMoving = false;
    public int targetX, targetY;
    public PImage currentImage;
    public PImage destinationTileImage;
    public boolean fisrstSelect = true;
    public boolean frozen = false;
    public boolean restart = false;
    public int pieceLogLocation = -1;
    public int pieceMoveLogLocation = -1;

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
        String timeTextWhite = showTimer(whitePlayerTimer);
        String timeTextBlack = showTimer(whitePlayerTimer);

        textSize(32);
        textAlign(CENTER);
        text(timeTextWhite, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT - CELLSIZE);
        text(timeTextBlack, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), CELLSIZE);
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
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    public void keyPressed(KeyEvent e) {
        String pressed = String.valueOf(e.getKey());
        if (pressed.equals("e") || pressed.equals("E")) {
            frozen = true;
            System.out.println(pressed);
            String checkText = "You resigned!";
            fill(255);
            textSize(15);
            textAlign(CENTER, CENTER);
            text(checkText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 3 * 2);

        }
        if (pressed.equals("r") || pressed.equals("R")) {
            loop();
            restart = true;
            fisrstSelect = true;
            frozen = false;
            pieceLogLocation = -1;
            pieceMoveLogLocation = -1;
            whitePlayerTimer = 180;
            blackPlayerTimer = 180;
            setup();
            redraw();
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
    public void keyReleased() {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (!frozen) {
            int mappingTileID = mappingTiles(e.getX(), e.getY());
            int[] xy = getXY(mappingTileID);

            if (e.getButton() == RIGHT) {
                tile = null;
                destinationTile = null;
                playerMovedPiece = null;
                redraw();
//            System.out.println("RIGHT" + mouseButton);
            } else if (e.getButton() == LEFT) {
                if (tile == null && e.getX() <= CELLSIZE * BOARD_WIDTH) { // First selected, chose piece
                    fisrstSelect = true;
                    tile = chessBoard.getTile(mappingTileID); // Get current tile
                    System.out.println("Tile:" + tile.getTileNowLocation());
                    playerMovedPiece = tile.getPiece();
                    if (playerMovedPiece == null || playerMovedPiece.pieceColour() != chessBoard.currentPlayer().pieceColour()) {
                        System.out.println("Tile:Null");
                        tile = null;
                    } else {
                        currentImage = chessTypeImage.get(playerMovedPiece.getPieceColorType());
                        pieceLogLocation = playerMovedPiece.pieceLocation();
                        // 动画
                        isMoving = true;
                        System.out.println("Color:" + playerMovedPiece.pieceColour());
                        System.out.println("Type:" + playerMovedPiece.getPieceColorType());
                        System.out.println("-----------");
                        redraw();// Highlight
                    }
//                }
                } else if (e.getX() <= CELLSIZE * BOARD_WIDTH) {// Second selected, move piece
                    fisrstSelect = false;
                    destinationTile = chessBoard.getTile(mappingTileID); // Get destination tile

                    System.out.println("Tile:" + tile.getTileNowLocation());
                    System.out.println("destinationTile:" + destinationTile.getTileNowLocation());
                    Move move = Move.MoveFactory.createMove(chessBoard, tile.getTileNowLocation(), destinationTile.getTileNowLocation());
                    if (move != Move.MoveFactory.getNullMove()) {
                        MoveToTile moveToTile = chessBoard.currentPlayer().makeMove(move);
                        if (moveToTile.getMoveCheck().isDone()) {//移动完成
                            pieceMoveLogLocation = move.getDestination();
                            // 时间+2s
                            if (chessBoard.currentPlayer().pieceColour().equals(PieceColour.WHITE)) {
                                whitePlayerTimer += 2;
                            } else if (chessBoard.currentPlayer().pieceColour().equals(PieceColour.BLACK)) {
                                blackPlayerTimer += 2;
                            }
                            //获取当前新游戏板
                            chessBoard = moveToTile.getBoard();
                            System.out.println(chessBoard.toString()); //print gameBoard
                            // 重画
                            redraw();
                            if (isMoving) {
                                targetX = xy[0];
                                targetY = xy[1];
                            }
                        }
                    }
                    tile = null;
                    destinationTile = null;
                    playerMovedPiece = null;
                    if (chessBoard.currentPlayer().inCheck()) {
//                        inCheck = true;
                        int kingLocation = chessBoard.currentPlayer().getPlayerKing().pieceLocation();
                        System.out.println("KingLocation:" + kingLocation);
                        PImage kingImage = chessTypeImage.get(chessBoard.currentPlayer().getPlayerKing().getPieceColorType());
                        int[] kingXY = getXY(kingLocation);
                        fill(215, 0, 0);
                        rect(kingXY[1] * CELLSIZE, kingXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                        image(kingImage, kingXY[1] * CELLSIZE, kingXY[0] * CELLSIZE);
                        String checkText = "Check!"; //"You must defend \n your king!";
                        fill(255);
                        textSize(10);
                        textAlign(CENTER, CENTER);
                        text(checkText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                        if (chessBoard.currentPlayer().getEnemy().checkMate()) {
                            frozen = true;
                            String checkMateText = "You lose by checkmate!";
                            fill(255);
                            textSize(10);
                            textAlign(CENTER, CENTER);
                            text(checkMateText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                        }
                    }
                }
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
        if (!frozen || (frozen && restart)) {
            background(180, 180, 180);
            fill(180, 180, 180);
            //Timer
            frame = frameCount;
            if (chessBoard.currentPlayer().pieceColour().white() && (frame % 60 == 0)) { // 如果是白棋玩家
                whitePlayerTimer -= 1;
            } else if (chessBoard.currentPlayer().pieceColour().black() && (frame % 60 == 0)) {
                blackPlayerTimer -= 1;
            }
            // show timer
            String timeTextWhite = showTimer(whitePlayerTimer);
            fill(255);
            textSize(32);
            textAlign(CENTER);
            text(timeTextWhite, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT - CELLSIZE);
            String timeTextBlack = showTimer(blackPlayerTimer);
            fill(255);
            textSize(32);
            textAlign(CENTER);
            text(timeTextBlack, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), CELLSIZE);
            // show gameBoard

            // Human
            if (!chessBoard.currentPlayer().pieceColour().setComputer()) {
                for (Tile tile : chessBoard.gameBoard) {
                    int tID = tile.getTileNowLocation();
//              System.out.println("ID:" + tID + tile.isTileOccupied());
                    int[] xy = getXY(tID);
                    // board重画
                    if ((xy[0] + xy[1]) % 2 == 0) {
                        fill(240, 217, 181);
                        rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    } else {
                        fill(181, 136, 99);
                        rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    }
                    if (!chessBoard.currentPlayer().inCheck()){
                        // log
                        if (tID == pieceLogLocation) {
                            fill(104, 137, 75);
                            int[] currentPieceXY = getXY(tID);
                            rect(currentPieceXY[1] * CELLSIZE, currentPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                        }
                        if (tile.isTileOccupied() && tID == pieceMoveLogLocation && !fisrstSelect) {
                            fill(104, 137, 75);
                            int[] logPieceXY = getXY(tID);
                            rect(logPieceXY[1] * CELLSIZE, logPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                            image(chessTypeImage.get(tile.getPiece().getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE);
                        }
                    }
                    //棋子重画
                    if (tile.isTileOccupied() && fisrstSelect == true && pieceMoveLogLocation == tID) {
                        image(chessTypeImage.get(tile.getPiece().getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE); //第一次直接画棋子
                    } else if (tile.isTileOccupied() && playerMovedPiece != tile.getPiece()) {
                        image(chessTypeImage.get(tile.getPiece().getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE); //第二次点击移动直接画棋子,如果没有第二次点击游戏版棋子为空
                    }
                    //highlight
                    if (fisrstSelect && playerMovedPiece != null) {
                        if (chessBoard.currentPlayer().inCheck()) {
                            String checkText = "You must defend \n your king!";
                            fill(255);
                            textSize(10);
                            textAlign(CENTER, CENTER);
                            text(checkText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 3 * 2);
                        }
                        for (Move move : pieceLegalMoves(chessBoard, playerMovedPiece)) { // fill legalMoves
                            if (move.getDestination() == tID) {
                                if ((xy[0] + xy[1]) % 2 == 0) {
                                    fill(196, 224, 232);
                                    rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                                } else {
                                    fill(170, 210, 221);
                                    rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                                }
                                if (chessBoard.getTile(tID).isTileOccupied() && (move.getMovePiece().pieceColour() != chessBoard.getTile(tID).getPiece().pieceColour())) {
                                    destinationTileImage = chessTypeImage.get(chessBoard.getTile(tID).getPiece().getPieceColorType());
                                    fill(253, 163, 102);
                                    rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                                    image(destinationTileImage, xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                                }
                            }
                        }
                    }
                    //moveChose
                    if (fisrstSelect && playerMovedPiece != null && chessBoard.currentPlayer().pieceColour().equals(playerMovedPiece.pieceColour())) {
                        fill(104, 137, 75);
                        int currentPieceLocation = playerMovedPiece.pieceLocation();
                        int[] currentPieceXY = getXY(currentPieceLocation);
                        rect(currentPieceXY[1] * CELLSIZE, currentPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                        image(currentImage, currentPieceXY[1] * CELLSIZE, currentPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);

                    }

                    // inCheck
                    if (chessBoard.currentPlayer().inCheck()) {
//                        inCheck = true;
                        int kingLocation = chessBoard.currentPlayer().getPlayerKing().pieceLocation();
                        System.out.println("KingLocation:" + kingLocation);
                        PImage kingImage = chessTypeImage.get(chessBoard.currentPlayer().getPlayerKing().getPieceColorType());
                        int[] kingXY = getXY(kingLocation);
                        fill(215, 0, 0);
                        rect(kingXY[1] * CELLSIZE, kingXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                        image(kingImage, kingXY[1] * CELLSIZE, kingXY[0] * CELLSIZE);
                        String checkText = "Check!"; //"You must defend \n your king!";
                        fill(255);
                        textSize(10);
                        textAlign(CENTER, CENTER);
                        text(checkText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                    }
                    if (chessBoard.currentPlayer().checkMate()) {
                        frozen = true;
                        String checkText = "You lose by checkmate!";
                        fill(255);
                        textSize(10);
                        textAlign(CENTER, CENTER);
                        text(checkText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                    }
                }
            }

            // AI 移动
            if (chessBoard.currentPlayer().pieceColour().setComputer()) {

                List<Piece> allAiPiece = chessBoard.currentPlayer().allActivePieces();
                List<Move> possibleMove = chessBoard.currentPlayer().getAllLegalMoves();
//                Piece movedPiece = allAiPiece.get((int) (Math.random() * allAiPiece.size()));
                boolean select = true;
//                List<Move> possibleMove = movedPiece.possibleMoves(chessBoard);
                int siz = (int) (Math.random() * possibleMove.size());
                Move randomMove = possibleMove.get(siz);
                MoveToTile movedAiTile = chessBoard.currentPlayer().makeMove(randomMove);
                Piece randomPiece = randomMove.getMovePiece();
                pieceLogLocation = randomMove.getCurrentLocation();
                pieceMoveLogLocation = randomMove.getDestination();
                for (Tile tile : chessBoard.gameBoard) {
                    int tID = tile.getTileNowLocation();
                    int[] xy = getXY(tID);
                    // board重画
                    if ((xy[0] + xy[1]) % 2 == 0) {
                        fill(240, 217, 181);
                        rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    } else {
                        fill(181, 136, 99);
                        rect(xy[1] * CELLSIZE, xy[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    }
                    if (!chessBoard.currentPlayer().inCheck()){
                        // log
                        if (tID == pieceLogLocation) {
                            fill(104, 137, 75);
                            int[] currentPieceXY = getXY(tID);
                            rect(currentPieceXY[1] * CELLSIZE, currentPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                        }
                        if (tile.isTileOccupied() && tID == pieceMoveLogLocation) {
                            fill(104, 137, 75);
                            int[] logPieceXY = getXY(tID);
                            rect(logPieceXY[1] * CELLSIZE, logPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                            image(chessTypeImage.get(randomPiece.getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE);
                        }
                    }

                    //棋子重画
                    if (tile.isTileOccupied() && select == true) {
                        image(chessTypeImage.get(tile.getPiece().getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE); //第一次直接画棋子
//                        if(randomPiece)
                        select = false;
                    } else if (tile.isTileOccupied() && randomPiece != tile.getPiece()) {
                        image(chessTypeImage.get(tile.getPiece().getPieceColorType()), xy[1] * CELLSIZE, xy[0] * CELLSIZE); //第二次点击移动直接画棋子
                    }
//                    if (!select && randomPiece != null && chessBoard.currentPlayer().pieceColour().equals(randomPiece.pieceColour())) {
//                        fill(104, 137, 75);
//                        int[] currentPieceXY = getXY(randomPieceLocation);
//                        rect(currentPieceXY[1] * CELLSIZE, currentPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
//                        image(chessTypeImage.get(randomPiece.getPieceColorType()), currentPieceXY[1] * CELLSIZE, currentPieceXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
//                    }

                }
                chessBoard = movedAiTile.getBoard();
                if (chessBoard.currentPlayer().inCheck()) {
                    int kingLocation = chessBoard.currentPlayer().getPlayerKing().pieceLocation();
                    System.out.println("KingLocation:" + kingLocation);
                    PImage kingImage = chessTypeImage.get(chessBoard.currentPlayer().getPlayerKing().getPieceColorType());
                    int[] kingXY = getXY(kingLocation);
                    fill(215, 0, 0);
                    rect(kingXY[1] * CELLSIZE, kingXY[0] * CELLSIZE, CELLSIZE, CELLSIZE);
                    image(kingImage, kingXY[1] * CELLSIZE, kingXY[0] * CELLSIZE);
                    String checkText = "Check!"; //"You must defend \n your king!";
                    fill(255);
                    textSize(10);
                    textAlign(CENTER, CENTER);
                    text(checkText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 3 * 2);
                    if (chessBoard.currentPlayer().checkMate()) {
                        String checkMateText = "You won by checkmate!";
                        fill(255);
                        textSize(10);
                        textAlign(CENTER, CENTER);
                        text(checkMateText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                        frozen = true;
                        noLoop();

                    }
                }
            }
            if (whitePlayerTimer <= 0 || blackPlayerTimer <= 0){
                frozen = true;
                String checkMateText = "You lose on time!";
                fill(255);
                textSize(10);
                textAlign(CENTER, CENTER);
                text(checkMateText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                frozen = true;
                noLoop();
            }
            if ( blackPlayerTimer <= 0){
                frozen = true;
                String checkMateText = "You won on time!";
                fill(255);
                textSize(10);
                textAlign(CENTER, CENTER);
                text(checkMateText, (CELLSIZE * BOARD_WIDTH) + (SIDEBAR / 2), HEIGHT / 2);
                frozen = true;
                noLoop();
            }
        }
    }

    void frameRateChanged() {
        frameRate(FPS);
    }
    // Add any additional methods or attributes you want. Please put classes in different files.

    public static void main(String[] args) {
        PApplet.main("XXLChess.App");
        new Sound("chessBGM.wav");
    }

}
