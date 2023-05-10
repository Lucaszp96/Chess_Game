package XXLChess.Piece;

import XXLChess.Player.BlackPlayer;
import XXLChess.Player.Player;
import XXLChess.Player.WhitePlayer;

public enum PieceColour {
    WHITE(){
        @Override
        public int getUp() {
            return up;
        }
        @Override
        public int getDown() {
            return down;
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
    },
    BLACK(){
        public int getUp() {
            return up;
        }
        @Override
        public int getDown() {
            return down;
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
    };


//    PieceColour(int num) {
//        this.num = num;
//    }
    int up = -1;
    int down = 1;
    public abstract int getUp();
    public abstract int getDown();
    public abstract boolean white();
    public abstract boolean black();


    public abstract  Player setPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
