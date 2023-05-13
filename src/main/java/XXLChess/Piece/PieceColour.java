package XXLChess.Piece;

import XXLChess.Board.BoardUtils;
import XXLChess.Player.BlackPlayer;
import XXLChess.Player.Player;
import XXLChess.Player.WhitePlayer;

public enum PieceColour {
    WHITE(){
        @Override
        public int getUp() {
            return -1;
        }
        @Override
        public int getDown() {
            return 1;
        }
        public boolean white(){
            return true;
        }
        public boolean black(){
            return false;
        }
        @Override
        public Player setPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return whitePlayer;
        }
        @Override
        public String toString() {
            return "White";
        }
        @Override
        public boolean pawnPromotion(int location) {
            return BoardUtils.SEVENTH_RANK[location];
        }
    },
    BLACK(){
        public int getUp() {
            return -1;
        }
        @Override
        public int getDown() {
            return 1;
        }
        public boolean white(){
            return false;
        }
        public boolean black(){
            return true;
        }
        @Override
        public Player setPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return blackPlayer;
        }
        @Override
        public String toString() {
            return "Black";
        }
        @Override
        public boolean pawnPromotion(int location) {
            return BoardUtils.EIGHTH_RANK[location];
        }
    };
    public abstract int getUp();
    public abstract int getDown();
    public abstract boolean white();
    public abstract boolean black();
    public abstract boolean pawnPromotion(int location);
    public abstract  Player setPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
