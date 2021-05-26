package chesspuzzle.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessPuzzleModelTest {

    ChessPuzzleModel underTest;

    @BeforeEach
    void setUp() {
        underTest = new ChessPuzzleModel();
    }

    @Test
    void testOneArgConstructor_shouldThrowException(){
        assertThrows(IllegalArgumentException.class, () -> new ChessPuzzleModel(null));
        assertThrows(IllegalArgumentException.class, () -> new ChessPuzzleModel(new Piece[][] {
                {Piece.ROOK},
                {Piece.EMPTY}})
        );
        assertThrows(IllegalArgumentException.class, () -> new ChessPuzzleModel(new Piece[][] {
                {Piece.ROOK, Piece.ROOK, Piece.ROOK},
                {Piece.EMPTY}})
        );
    }

    @Test
    void testCanMove() {
        assertTrue(underTest.canMove(0,1));
        assertTrue(underTest.canMove(1,1));
        assertFalse(underTest.canMove(0,0));
        assertFalse(underTest.canMove(0,2));
        assertFalse(underTest.canMove(1,0));
        assertFalse(underTest.canMove(1,2));
    }

    @Test
    void testEmptyRow() {
        assertEquals(underTest.emptyRow(), 1);
    }

    @Test
    void testEmptyCol() {
        assertEquals(underTest.emptyCol(), 2);
    }

    @Test
    void testIsGameWon() {
        assertFalse(underTest.isGameWon());
        assertTrue(new ChessPuzzleModel(new Piece[][] {
                {Piece.BISHOP, Piece.BISHOP, Piece.EMPTY},
                {Piece.ROOK, Piece.ROOK, Piece.KING}}).isGameWon());
    }
}