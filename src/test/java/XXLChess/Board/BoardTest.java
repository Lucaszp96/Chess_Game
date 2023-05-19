package XXLChess.Board;

import XXLChess.Move.Move;
import XXLChess.Piece.Piece;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static XXLChess.App.chessListConfig;
import static XXLChess.App.configPath;
import static XXLChess.Board.ReadConfig.chessConfig;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void initialBoard() {
        File chessLayout = new File("level1.txt");
        List<String> chessListConfig = chessConfig(chessLayout);
        final Board board = Board.initialBoard(chessListConfig);

        assertEquals(board.currentPlayer().getAllLegalMoves().size(), 46);
        assertEquals(board.currentPlayer().getEnemy().getAllLegalMoves().size(), 46);
        assertFalse(board.currentPlayer().inCheck());
        assertFalse(board.currentPlayer().checkMate());
        assertEquals(board.currentPlayer(), board.getWhitePlayer());
        assertEquals(board.currentPlayer().getEnemy(), board.getBlackPlayer());
        assertEquals(board.getWhitePieces().size(), 28);
        assertEquals(board.getBlackPieces().size(), 28);
    }
}