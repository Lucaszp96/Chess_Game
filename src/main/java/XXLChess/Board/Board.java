package XXLChess.Board;

import XXLChess.Move.Move;
import XXLChess.Piece.*;
import XXLChess.Player.BlackPlayer;
import XXLChess.Player.Player;
import XXLChess.Player.WhitePlayer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.*;

import static XXLChess.App.BOARD_WIDTH;
import static XXLChess.App.TILES_NUM;

public class Board {

    public final List<Tile> gameBoard; // creat 14*14 game board list mapping
    private  List<Piece> whitePieces;
    private  List<Piece> blackPieces;
    private WhitePlayer whitePlayer;
    private BlackPlayer blackPlayer;
    private Player currentPlayer;
    private  Board(BoardBuilder boardBuilder){
        this.gameBoard = createGameBoard(boardBuilder);
        this.whitePieces = allPieceOnBoard(this.gameBoard, PieceColour.WHITE); //All white pieces
        this.blackPieces = allPieceOnBoard(this.gameBoard, PieceColour.BLACK); //All black pieces
        List<Move> whitePieceMoves = legalMoves(this.whitePieces);
        List<Move> blackPieceMoves = legalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whitePieceMoves, blackPieceMoves);
        this.blackPlayer = new BlackPlayer(this, blackPieceMoves, whitePieceMoves);
        this.currentPlayer = boardBuilder.movePlayer.setPlayer(this.whitePlayer, this.blackPlayer);
    }
    public Tile getTile(int destination) {

        return gameBoard.get(destination);
    }
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }
    public List<Piece> getWhitePieces(){
        return this.whitePieces;
    }
    public Player currentPlayer() {
        return this.currentPlayer;
    }
    public List<Piece> getBlackPieces(){
        return this.blackPieces;
    }
    public static boolean isValid(int currentTile) {
        return currentTile >= 0 && currentTile < (BOARD_WIDTH * BOARD_WIDTH);
    }
    public List<Move> legalMoves(List<Piece> pieces) { //Add all pieces possibleMoves to legalMoves
        List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces){
            legalMoves.addAll(piece.possibleMoves(this));
        }
        return legalMoves;
    }
    public Iterable<Move> getAllLegalMoves(){
//        List<Move> whitePlayerAllLegalMoves = this.whitePlayer.getAllLegalMoves();
//        List<Move> blackPlayerAllLegalMoves = this.blackPlayer.getAllLegalMoves();
        return Iterables.unmodifiableIterable(Iterables.concat(this.whitePlayer.getAllLegalMoves(), this.blackPlayer.getAllLegalMoves()));
    }
    public static List<Piece> allPieceOnBoard(List<Tile> gameBoard, PieceColour pieceColour) {// tracking all pieces(BLACK/WHITE)
        List<Piece> piecesOnBoard = new ArrayList<>();
        for (Tile tile : gameBoard){
            if (tile.getPiece()!= null){
                Piece piece = tile.getPiece();
                if (piece.pieceColour() == pieceColour) {
                    piecesOnBoard.add(piece);
                }
            }
        }
        return piecesOnBoard;
    }

    // this method is creat 14*14 game board mapping
    private static List<Tile> createGameBoard(BoardBuilder boardBuilder){
        Tile[] tiles = new Tile[TILES_NUM];
        for (int i = 0; i< TILES_NUM; i++){
            tiles[i] = Tile.createTile(i,boardBuilder.boardCode.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }
    public static Board initialBoard(List<String> chessListConfig){

        BoardBuilder builder = new BoardBuilder();
        for (int i = 0 ; i< TILES_NUM; i++){
            if (chessListConfig.get(i) == " "){
                continue;
            }
//            if (Character.isUpperCase(chessListConfig.get(i).charAt(0))){
                if(chessListConfig.get(i).equals("P")){
                    builder.setPiece(new Pawn(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("R")) {
                    builder.setPiece(new Rook(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("N")) {
                    builder.setPiece(new Knight(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("B")) {
                    builder.setPiece(new Bishop(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("H")) {
                    builder.setPiece(new Archbishop(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("C")) {
                    builder.setPiece(new Camel(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("G")) {
                    builder.setPiece(new Guard(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("A")) {
                    builder.setPiece(new Amazon(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("K")) {
                    builder.setPiece(new King(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("E")) {
                    builder.setPiece(new Chancellor(i, PieceColour.BLACK));//TODO not finished Chancellor
                } else if (chessListConfig.get(i).equals("Q")) {
                    builder.setPiece(new Queen(i, PieceColour.BLACK));
                }
//            }else{ //
                if(chessListConfig.get(i).equals("p")){
                    builder.setPiece(new Pawn(i, PieceColour.WHITE));
                } else if (chessListConfig.get(i).equals("r")) {
                    builder.setPiece(new Rook(i, PieceColour.WHITE));
                } else if (chessListConfig.get(i).equals("n")) {
                    builder.setPiece(new Knight(i, PieceColour.WHITE));
                } else if (chessListConfig.get(i).equals("b")) {
                    builder.setPiece(new Bishop(i, PieceColour.WHITE));
                } else if (chessListConfig.get(i).equals("h")) {
                    builder.setPiece(new Archbishop(i, PieceColour.WHITE));
                } else if (chessListConfig.get(i).equals("c")) {
                    builder.setPiece(new Camel(i, PieceColour.WHITE));// not finished
                } else if (chessListConfig.get(i).equals("g")) {
                    builder.setPiece(new Guard(i, PieceColour.WHITE));// not finished
                } else if (chessListConfig.get(i).equals("a")) {
                    builder.setPiece(new Amazon(i, PieceColour.WHITE));// not finished
                } else if (chessListConfig.get(i).equals("k")) {
                    builder.setPiece(new King(i, PieceColour.WHITE));
                } else if (chessListConfig.get(i).equals("e")) {
                    builder.setPiece(new Chancellor(i, PieceColour.WHITE));// not finished
                } else if (chessListConfig.get(i).equals("q")) {
                    builder.setPiece(new Queen(i, PieceColour.WHITE));
                }
//            }
        }
        builder.setMovePlayer(PieceColour.WHITE);
        return builder.build();
    }

    // Builder can solve the problems which constructor have a lot of parameters
    public static class BoardBuilder{
        public static Map<Integer, Piece> boardCode;//boardconfig
        public static PieceColour movePlayer;//nextMovemaker
        public BoardBuilder(){
            this.boardCode = new HashMap<>();
        }
        public BoardBuilder setPiece(Piece piece){ // move piece to new location
            this.boardCode.put(piece.pieceLocation(), piece);
            return this;
        }
        public BoardBuilder setMovePlayer(PieceColour movePlayer) {
            this.movePlayer = movePlayer;
//            System.out.println("movePlayer:" + movePlayer);
            return this;
        }
        public Board build(){
            return new Board(this);
        }
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<TILES_NUM; i++){
            String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i+1)%BOARD_WIDTH == 0){
                builder.append("\n");
            }
        }
        return builder.toString();
    }




}
