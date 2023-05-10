package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;
import XXLChess.Piece.Rook;

public class CastleMove extends Move{
    protected Rook castlingRook;
    protected int castlingRookLocation;
    protected int castlingRookDestination;
    public Rook getCastlingRook() {
        return this.castlingRook;
    }
    @Override
    public boolean castlingMove(){
        return true;
    }
    public CastleMove(Board board, Piece movePiece, int destination, Rook castlingRook, int castlingRookLocation, int castlingRookDestination) {
        super(board, movePiece, destination);
        this.castlingRook = castlingRook;
        this.castlingRookLocation = castlingRookLocation;
        this.castlingRookDestination = castlingRookDestination;
    }
    @Override
    public Board active(){
        Board.BoardBuilder builder = new Board.BoardBuilder();
        for (Piece piece : this.board.currentPlayer().allActivePieces()){ // Gain current player's all pieces
            if (!this.movePiece.equals(piece) && !this.castlingRook.equals(piece)){ //if the piece in current player is not a move piece, set them into new board(no change), only moved piece do next step
                builder.setPiece(piece);
            }
        }
        for (Piece piece : this.board.currentPlayer().getEnemy().allActivePieces()) { // Gain opponent player's all pieces
            builder.setPiece(piece);
        }
        builder.setPiece(this.movePiece.movePiece(this));
        builder.setPiece(new Rook(this.castlingRookDestination, this.castlingRook.pieceColour())); //TODO fist move
        builder.setMovePlayer(this.board.currentPlayer().getEnemy().pieceColour());
        return builder.build();
    }

}
