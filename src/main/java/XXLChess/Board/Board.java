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
    /**
     * Board constructor.
     *<p>Create a Board instance through this method. It includes game board, ceramic lattice, white player, black player, white player piece, black player piece, current player.<br>
     * @param boardBuilder boardBuilder is a Builder, Builder can solve the problems which constructor have a lot of parameters.
     */
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
    /**
     * getTile is able to fetch every tile on the game board.
     * @param destination int type parameter, specifying the tiles position.
     * @return Returns the tile specified by the game board.
     */
    public Tile getTile(int destination) {

        return gameBoard.get(destination);
    }
    /**
     * Get white player.
     * @return Returns white player.
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }
    /**
     * Get black player.
     * @return Returns black player.
     */
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }
    /**
     * Get all white player's pieces.
     * @return Returns all white player's pieces.
     */
    public List<Piece> getWhitePieces(){
        return this.whitePieces;
    }
    /**
     * Get current player.
     * @return Returns current player.
     */
    public Player currentPlayer() {
        return this.currentPlayer;
    }
    /**
     * Get all black player's pieces.
     * @return Returns all black player's pieces.
     */
    public List<Piece> getBlackPieces(){
        return this.blackPieces;
    }
    /**
     * Determine whether the current tile position is legal.
     * @param currentTile int type parameter, current tile position.
     * @return
     */
    public static boolean isValid(int currentTile) {
        return currentTile >= 0 && currentTile < (BOARD_WIDTH * BOARD_WIDTH);
    }
    /**
     * List stores the legal moves of all chess pieces.
     * @param pieces A list of chess pieces.
     * @return Returns a list stores the legal moves of all chess pieces.
     */
    public List<Move> legalMoves(List<Piece> pieces) { //Add all pieces possibleMoves to legalMoves
        List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces){
            legalMoves.addAll(piece.possibleMoves(this));
        }
        return legalMoves;
    }
    /**
     * Get the legal moves of all pieces on the current board.
     */
    public Iterable<Move> getAllLegalMoves(){
        return Iterables.unmodifiableIterable(Iterables.concat(this.whitePlayer.getAllLegalMoves(), this.blackPlayer.getAllLegalMoves()));
    }
    /**
     * Get all the pieces of the specified color on the current gameboard.
     * @param gameBoard Current game board.
     * @param pieceColour Specified piece color.
     * @return A list stores all pieces specified color.
     */
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
    /**
     * Create a game board through tiles.
     * @param boardBuilder boardBuilder is a Builder, Builder can solve the problems which constructor have a lot of parameters.
     * @return A list stores all tiles.
     */
    // this method is creat 14*14 game board mapping
    private static List<Tile> createGameBoard(BoardBuilder boardBuilder){
        Tile[] tiles = new Tile[TILES_NUM];
        for (int i = 0; i< TILES_NUM; i++){
            tiles[i] = Tile.createTile(i,boardBuilder.boardCode.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }
    /**
     * Initialize a game board.
     * <p> Create the initial chessboard by reading the chessboard configuration list to obtain the initial chess piece position. <br>
     * @param chessListConfig Chessboard configuration list.
     * @return Returns Builder build() method.
     */
    public static Board initialBoard(List<String> chessListConfig){
        BoardBuilder builder = new BoardBuilder();
        for (int i = 0 ; i< TILES_NUM; i++){
            if (chessListConfig.get(i) == " "){
                continue;
            }
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
                    builder.setPiece(new King(i, PieceColour.BLACK, true));
                } else if (chessListConfig.get(i).equals("E")) {
                    builder.setPiece(new Chancellor(i, PieceColour.BLACK));
                } else if (chessListConfig.get(i).equals("Q")) {
                    builder.setPiece(new Queen(i, PieceColour.BLACK));
                }
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
                    builder.setPiece(new King(i, PieceColour.WHITE, true));
                } else if (chessListConfig.get(i).equals("e")) {
                    builder.setPiece(new Chancellor(i, PieceColour.WHITE));// not finished
                } else if (chessListConfig.get(i).equals("q")) {
                    builder.setPiece(new Queen(i, PieceColour.WHITE));
                }
        }
        builder.setMovePlayer(PieceColour.WHITE);
        return builder.build();
    }
    // Builder can solve the problems which constructor have a lot of parameters
    public static class BoardBuilder{
        public static Map<Integer, Piece> boardCode;
        public static PieceColour movePlayer;
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
