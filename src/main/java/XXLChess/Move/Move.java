package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public abstract class Move {
    final Board board;
    final Piece movePiece;
    final int destination;
    public static Move NULLMOVE = new NullMove();

    public Move(Board board, Piece movePiece, int destination) {
        this.board = board;
        this.movePiece = movePiece;
        this.destination = destination;
    }
    public Piece getMovePiece() {
        return movePiece;
    }
    public int getCurrentLocation(){
        return this.getMovePiece().pieceLocation();
    }
    public int getDestination(){
        return this.destination;
    }
    public boolean underAttack(){
        return false;
    }
    public boolean castlingMove(){
        return false;
    }
    public Piece attackPiece(){
        return null;
    }
    @Override
    public int hashCode() {
        int constant = 31;
        int code = 1;
        code = constant * code + this.destination;
        code = constant * code + this.movePiece.hashCode();
        return code;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Move)){
            return false;
        }
        Move restOfMove = (Move) obj;
        return getDestination() == restOfMove.getDestination() && getMovePiece().equals(restOfMove.getMovePiece());
    }

    public Board active() {
        Board.BoardBuilder builder =new Board.BoardBuilder();
        for (Piece piece : this.board.currentPlayer().allActivePieces()){ // Gain current player's all pieces
            if (!this.movePiece.equals(piece)){ //if the piece in current player is not a move piece, set them into new board(no change), only moved piece do next step
                builder.setPiece(piece);
            }
        }
        for (Piece piece : this.board.currentPlayer().getEnemy().allActivePieces()) { // Gain opponent player's all pieces
            builder.setPiece(piece);
        }
        //TODO weather first move
        builder.setPiece(this.movePiece.movePiece(this)); // moved piece is moving
        builder.setMovePlayer(this.board.currentPlayer().getEnemy().pieceColour());
        return builder.build();
    }

    public static class MoveFactory{
        private MoveFactory(){
            throw new RuntimeException("Not instantiable!");
        }
        public static Move createMove(Board board, int currentTileLocation, int destinationTileLocation){
            for (Move move : board.getAllLegalMoves()){
                if (move.getCurrentLocation() == currentTileLocation && move.getDestination() == destinationTileLocation){
                    return move;
                }
            }
            return NULLMOVE;
        }
    }
}