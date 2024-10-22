import org.example.ConnectFour;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectFourTest {
    private ConnectFour game;

    @Before
    public void setUp() {
        game = new ConnectFour();
    }

    @Test
    public void testEmptyBoard() {
        assertFalse(game.isBoardFull());
    }

    @Test
    public void testCheckWinDiagonal() {
        game.throwChip(0, 'X');
        game.throwChip(1, 'O');
        game.throwChip(1, 'X');
        game.throwChip(2, 'O');
        game.throwChip(2, 'O');
        game.throwChip(2, 'X');
        game.throwChip(3, 'O');
        game.throwChip(3, 'O');
        game.throwChip(3, 'O');
        game.throwChip(3, 'X');
        assertTrue(game.checkWinner('X'));
    }

    @Test
    public void testCheckWinVertical() {
        game.throwChip(0, 'X');
        game.throwChip(0, 'X');
        game.throwChip(0, 'X');
        game.throwChip(0, 'X');
        assertTrue(game.checkWinner('X'));
    }

    @Test
    public void testCheckWinHorizontal() {
        game.throwChip(0, 'X');
        game.throwChip(1, 'X');
        game.throwChip(2, 'X');
        game.throwChip(3, 'X');
        assertTrue(game.checkWinner('X'));
    }

}
