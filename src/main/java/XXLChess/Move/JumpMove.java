package XXLChess.Move;

import XXLChess.Board.Board;
import XXLChess.Piece.Piece;

public class JumpMove extends Move{
    public JumpMove(Board board, Piece movePiece, int destination) {
        super(board, movePiece, destination);
    }

    @Override
    public Board active() {
        Board.BoardBuilder builder =new Board.BoardBuilder();
        for (Piece piece : this.board.currentPlayer().allActivePieces()){ // Gain current player's all pieces
            //TODO hashcode and equals for pieces
            if (!this.movePiece.equals(piece)){ //if the piece in current player is not a move piece, set them into new board(no change), only moved piece do next step
                builder.setPiece(piece);
            }
        }
        for (Piece piece : this.board.currentPlayer().getEnemy().allActivePieces()) { // Gain opponent player's all pieces
            builder.setPiece(piece);
        }
        builder.setPiece(this.movePiece.movePiece(this)); // moved piece is moving
        builder.setMovePlayer(this.board.currentPlayer().getEnemy().pieceColour());
        return builder.build();
    }
}
