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

public class WhitePlayer extends Player{
    public WhitePlayer(Board board, List<Move> whiteLegalMoves, List<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }
    @Override
    public List<Piece> allActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public PieceColour pieceColour() {
        return PieceColour.WHITE;
    }
    @Override
    public Player getEnemy() {
        return this.board.getBlackPlayer();
    }
    @Override
    public List<Move> castling(List<Move> playerMoves, List<Move> enemyMoves) {
        List<Move> castling = new ArrayList<>();
        if (this.playerKing.firstMove() && !this.inCheck()){
            if (!this.board.getTile(190).isTileOccupied() &&
                    !this.board.getTile(191).isTileOccupied()){// right castling
                Tile rookTile = this.board.getTile(195);
                if(rookTile.isTileOccupied() && rookTile.getPiece().firstMove() && (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r"))){
                    if (Player.underAttacks(190, enemyMoves).isEmpty() &&
                            Player.underAttacks(191, enemyMoves).isEmpty() &&
                            (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r") )) { //king 189
                        castling.add(new CastleMove(this.board, this.playerKing, 191, (Rook) rookTile.getPiece(), rookTile.getTileNowLocation(), 190)); //playerKing.pieceLocation()+1
                    }
                }
            }
            if (!this.board.getTile(188).isTileOccupied() &&
                    !this.board.getTile(187).isTileOccupied()){// left castling
                Tile rookTile = this.board.getTile(182);
                if(rookTile.isTileOccupied() && rookTile.getPiece().firstMove() && (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r"))){
                    if (Player.underAttacks(188, enemyMoves).isEmpty() &&
                            Player.underAttacks(187, enemyMoves).isEmpty() &&
                            (rookTile.getPiece().getPieceColorType().equals("R") || rookTile.getPiece().getPieceColorType().equals("r"))){
                        castling.add(new CastleMove(this.board, this.playerKing, 187, (Rook) rookTile.getPiece(), rookTile.getTileNowLocation(), 188));
                    }
                }
            }
        }
        return castling;
    }
}
