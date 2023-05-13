package XXLChess.Board;

import XXLChess.Move.Move;
import XXLChess.Piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HighlightLegalMoves {
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
