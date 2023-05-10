package XXLChess.Move;

public enum MoveCheck {
    DONE{
        @Override
        public boolean isDone(){
            return true;
        }
    },
    ILLEGAL_MOVE{
        @Override
        public boolean isDone(){
            return false;
        }
    },
    PLAYER_IN_CHECK{
        @Override
        public boolean isDone(){
            return false;
        }
    };
    public abstract boolean isDone();
}
