package XXLChess.Board;

import XXLChess.Move.Move;
import XXLChess.Piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HighlightLegalMoves {
    /**
     * Get a legal move for a specified piece.
     * <p> If the player currently has no checkers selected, or an enemy checker is selected, returns an empty list.<br>
     * @param board Current game board.
     * @param playerMovedPiece The chess piece selected by the current player.
     * @return A a legal move for a specified piece. Or empty list.
     */
    public static List<Move> pieceLegalMoves(Board board,  Piece playerMovedPiece){
        if (playerMovedPiece != null && playerMovedPiece.pieceColour() == board.currentPlayer().pieceColour()){
            if (playerMovedPiece.getPieceType().equals("king")){
                playerMovedPiece.possibleMoves(board);
                return playerMovedPiece.possibleMoves(board);
            }
            return playerMovedPiece.possibleMoves(board);
        }
        return Collections.emptyList();
    }
}
