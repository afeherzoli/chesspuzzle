package chesspuzzle.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ChessPuzzleModel {

    public static int ROWS = 2;
    public static int COLUMNS = 3;

    public static final Piece[][] INITIAL = {
            {Piece.KING, Piece.BISHOP, Piece.BISHOP},
            {Piece.ROOK, Piece.ROOK, Piece.EMPTY}
    };

    private ReadOnlyObjectWrapper <Piece>[][] board = new ReadOnlyObjectWrapper[ROWS][COLUMNS];

    public ChessPuzzleModel(){
        this(INITIAL);
        /*board[0][0]= new ReadOnlyObjectWrapper<>(Piece.KING);
        board[0][1]= new ReadOnlyObjectWrapper<>(Piece.BISHOP);
        board[0][2]= new ReadOnlyObjectWrapper<>(Piece.BISHOP);
        board[1][0]= new ReadOnlyObjectWrapper<>(Piece.ROOK);
        board[1][1]= new ReadOnlyObjectWrapper<>(Piece.ROOK);
        board[1][2]= new ReadOnlyObjectWrapper<>(Piece.EMPTY);*/
    }

    public ChessPuzzleModel(Piece[][] a){
        if (!isValidBoard(a)){
            throw new IllegalArgumentException();
        }
        initBoard(a);
    }

    private boolean isValidBoard(Piece[][] a){
        if (a ==null || a.length != 2)
            return false;
        if (a[0].length != 3 || a[1].length != 3)
            return false;
        int king = 0;
        int rook = 0;
        int bishop = 0;
        int empty = 0;
        for (int i=0; i<ROWS; i++){
            for (int j=0; j<COLUMNS; j++){
                switch (a[i][j]){
                    case KING: {
                        king++;
                        break;
                    }
                    case ROOK:{
                        rook++;
                        break;
                    }
                    case BISHOP:{
                        bishop++;
                        break;
                    }
                    case EMPTY:{
                        empty++;
                    }
                }
            }
        }
        return king == 1 && bishop == 2 && rook == 2 && empty == 1;

    }

    private void initBoard(Piece[][] a){
        board[0][0]= new ReadOnlyObjectWrapper<>(a[0][0]);
        board[0][1]= new ReadOnlyObjectWrapper<>(a[0][1]);
        board[0][2]= new ReadOnlyObjectWrapper<>(a[0][2]);
        board[1][0]= new ReadOnlyObjectWrapper<>(a[1][0]);
        board[1][1]= new ReadOnlyObjectWrapper<>(a[1][1]);
        board[1][2]= new ReadOnlyObjectWrapper<>(a[1][2]);
    }

    public ReadOnlyObjectProperty<Piece> pieceProperty(int i, int j){

        return board[i][j].getReadOnlyProperty();
    }

    public boolean canMove(int i, int j){
        int emptyRow = emptyRow();
        int emptyCol = emptyCol();

        switch (board[i][j].get()){
            case KING:{
                return Math.abs(j - emptyCol) < 2;
            }
            case BISHOP:{
                return Math.abs(i - emptyRow) == 1 && Math.abs(j - emptyCol) == 1;
            }
            case ROOK:{
                return (Math.abs(i - emptyRow) == 1 && Math.abs(j-emptyCol)==0 ) || (Math.abs(i - emptyRow) == 0 && Math.abs(j-emptyCol)==1);
            }
        }
        return false;
    }

    public int emptyRow(){
        for (int i=0; i<ROWS; i++){
            for (int j=0; j<COLUMNS; j++){
                if (board[i][j].get() == Piece.EMPTY){
                    return i;
                }
            }
        }
        return -1;
    }

    public int emptyCol(){
        for (int i=0; i<ROWS; i++){
            for (int j=0; j<COLUMNS; j++){
                if (board[i][j].get() == Piece.EMPTY){
                    return j;
                }
            }
        }
        return -1;
    }

    public void move(int i, int j){
        int emptyRow = emptyRow();
        int emptyCol = emptyCol();
        if (canMove(i, j)){
            if (board[i][j].get() == Piece.KING)
                board[emptyRow][emptyCol].set(Piece.KING);
            else if (board[i][j].get() == Piece.BISHOP)
                board[emptyRow][emptyCol].set(Piece.BISHOP);
            else if  (board[i][j].get() == Piece.ROOK)
                board[emptyRow][emptyCol].set(Piece.ROOK);
        }
        board[i][j].set(Piece.EMPTY);

    }

    public boolean isGameWon(){
        return board[0][0].get() == Piece.BISHOP && board[0][1].get() == Piece.BISHOP && board[0][2].get() == Piece.EMPTY
                && board[1][0].get() == Piece.ROOK && board[1][1].get() == Piece.ROOK && board[1][2].get() == Piece.KING;
    }
}
