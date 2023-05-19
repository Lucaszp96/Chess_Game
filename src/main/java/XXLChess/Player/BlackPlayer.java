package XXLChess.Player;

import XXLChess.Board.Board;
import XXLChess.Board.Tile;
import XXLChess.Move.CastleMove;
import XXLChess.Move.Move;
import XXLChess.Piece.Piece;
import XXLChess.Piece.PieceColour;
import XXLChess.Piece.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board, List<Move> blackLegalMoves, List<Move> whiteLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }
    @Override
    public List<Piece> allActivePieces() {
        return this.board.getBlackPieces();
    }
    @Override
    public PieceColour pieceColour() {
        return PieceColour.BLACK;
    }
    @Override
    public Player getEnemy() {
        return this.board.getWhitePlayer();
    }
    @Override
    public List<Move> castling(List<Move> playerMoves, List<Move> enemyMoves) {
        List<Move> castling = new ArrayList<>();
        if (this.playerKing.firstMove() && !this.inCheck()){
            // right castling
            if (!this.board.getTile(8).isTileOccupied() && // The king piece right 1&2 tile is not occupied
                    !this.board.getTile(9).isTileOccupied()){
                Tile rookTile = this.board.getTile(13); // Gain the rook tile
                if(rookTile.isTileOccupied() && rookTile.getPiece().firstMove() && (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r"))){ // if rook first move and this tile is rook piece
                    if (Player.underAttacks(8, enemyMoves).isEmpty() &&  // If the king piece make castling move and not be attacked
                            Player.underAttacks(9, enemyMoves).isEmpty() &&
                            (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r") )){ // Rook tile's piece is Rook!
                        castling.add(new CastleMove(this.board, this.playerKing, 9,
                                        (Rook) rookTile.getPiece(), rookTile.getTileNowLocation(), 8));
                    }
                }
            }
            if (!this.board.getTile(6).isTileOccupied() &&
                    !this.board.getTile(5).isTileOccupied()){// left castling
                Tile rookTile = this.board.getTile(0);
                if(rookTile.isTileOccupied() && rookTile.getPiece().firstMove() && (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r"))){
                    if (Player.underAttacks(6, enemyMoves).isEmpty() &&
                            Player.underAttacks(5, enemyMoves).isEmpty() &&
                            (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r"))){
                        castling.add(new CastleMove(this.board, this.playerKing, 5,
                                        (Rook) rookTile.getPiece(), rookTile.getTileNowLocation(), 6));
                    }
                }
            }
        }
        return castling;
    }
}
