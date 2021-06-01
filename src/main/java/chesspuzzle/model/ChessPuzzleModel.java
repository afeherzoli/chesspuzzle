package chesspuzzle.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Class representing the state of the game.
 */
public class ChessPuzzleModel {

    /**
     * The number of rows on the board.
     */
    public static int ROWS = 2;

    /**
     * The number of columns on the board.
     */
    public static int COLUMNS = 3;

    /**
     * The array represents the initial configuration of the game board.
     */
    public static final Piece[][] INITIAL = {
            {Piece.KING, Piece.BISHOP, Piece.BISHOP},
            {Piece.ROOK, Piece.ROOK, Piece.EMPTY}
    };

    /**
     * The array that stores the configuration of the board.
     */
    private ReadOnlyObjectWrapper <Piece>[][] board = new ReadOnlyObjectWrapper[ROWS][COLUMNS];

    /**
     * Creates a {@code ChessPuzzleModel} object representing
     *  the initial state of the board.
     */
    public ChessPuzzleModel() {
        this(INITIAL);
    }

    /**
     * Creates a {@code ChessPuzzleModel} object which was
     *  initialized with the specified array.
     *
     * @param a is an array with the size of 2x3 containing the initial
     *  configuration of the board.
     * @throws IllegalArgumentException if the array doesnt represents
     *  a valid configuration for a board.
     */
    public ChessPuzzleModel(Piece[][] a) {
        if (!isValidBoard(a)) {
            throw new IllegalArgumentException();
        }
        initBoard(a);
    }

    private boolean isValidBoard(Piece[][] a) {
        if (a == null || a.length != 2)
            return false;
        if (a[0].length != 3 || a[1].length != 3)
            return false;
        int king = 0;
        int rook = 0;
        int bishop = 0;
        int empty = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                switch (a[i][j]) {
                    case KING: {
                        king++;
                        break;
                    }
                    case ROOK: {
                        rook++;
                        break;
                    }
                    case BISHOP: {
                        bishop++;
                        break;
                    }
                    case EMPTY: {
                        empty++;
                    }
                }
            }
        }
        return king == 1 && bishop == 2 && rook == 2 && empty == 1;
    }

    private void initBoard(Piece[][] a) {
        board[0][0] = new ReadOnlyObjectWrapper<>(a[0][0]);
        board[0][1] = new ReadOnlyObjectWrapper<>(a[0][1]);
        board[0][2] = new ReadOnlyObjectWrapper<>(a[0][2]);
        board[1][0] = new ReadOnlyObjectWrapper<>(a[1][0]);
        board[1][1] = new ReadOnlyObjectWrapper<>(a[1][1]);
        board[1][2] = new ReadOnlyObjectWrapper<>(a[1][2]);
    }

    public ReadOnlyObjectProperty<Piece> pieceProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    /**
     * @param i the row of the position
     * @param j the column of the position
     * @return if a piece in the given position can move to the empty space.
     */
    public boolean canMove(int i, int j) {
        int emptyRow = emptyRow();
        int emptyCol = emptyCol();

        switch (board[i][j].get()) {
            case KING: {
                return Math.abs(j - emptyCol) < 2;
            }
            case BISHOP: {
                return Math.abs(i - emptyRow) == 1 && Math.abs(j - emptyCol) == 1;
            }
            case ROOK: {
                return (Math.abs(i - emptyRow) == 1 && Math.abs(j - emptyCol) == 0)
                        || (Math.abs(i - emptyRow) == 0 && Math.abs(j - emptyCol) == 1);
            }
        }
        return false;
    }

    /**
     * {@return the row index of the empty space}.
     */
    public int emptyRow() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j].get() == Piece.EMPTY) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * {@return the column index of the empty space}.
     */
    public int emptyCol() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j].get() == Piece.EMPTY) {
                    return j;
                }
            }
        }
        return -1;
    }

    /**
     * Moves the chosen piece to the empty space.
     *
     * @param i row index of the chosen piece
     * @param j column index of the chosen piece
     */
    public void move(int i, int j) {
        int emptyRow = emptyRow();
        int emptyCol = emptyCol();
        if (canMove(i, j)) {
            if (board[i][j].get() == Piece.KING) {
                board[emptyRow][emptyCol].set(Piece.KING);
            } else if (board[i][j].get() == Piece.BISHOP) {
                board[emptyRow][emptyCol].set(Piece.BISHOP);
            } else if  (board[i][j].get() == Piece.ROOK) {
                board[emptyRow][emptyCol].set(Piece.ROOK);
            }
        }
        board[i][j].set(Piece.EMPTY);
    }

    /**
     * Return whether the pieces are in the game winning position.
     * @return {@code true} if board is in winning position, {@code false} otherwise
     */
    public boolean isGameWon() {
        return board[0][0].get() == Piece.BISHOP
                && board[0][1].get() == Piece.BISHOP
                && board[0][2].get() == Piece.EMPTY
                && board[1][0].get() == Piece.ROOK
                && board[1][1].get() == Piece.ROOK
                && board[1][2].get() == Piece.KING;
    }
}
