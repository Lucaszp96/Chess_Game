package XXLChess.Piece;

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
    };


//    PieceColour(int num) {
//        this.num = num;
//    }

    public abstract int getUp();
    public abstract int getDown();
    public abstract boolean white();
    public abstract boolean black();


    public abstract  Player setPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
