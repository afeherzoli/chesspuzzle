package chesspuzzle.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class ChessPuzzleModel {

    public static int ROWS = 2;
    public static int COLUMNS = 3;

    private ReadOnlyObjectWrapper <Piece>[][] board = new ReadOnlyObjectWrapper[ROWS][COLUMNS];

    public ChessPuzzleModel(){
        board[0][0]= new ReadOnlyObjectWrapper<Piece>(Piece.KING);
        board[0][1]= new ReadOnlyObjectWrapper<Piece>(Piece.BISHOP);
        board[0][2]= new ReadOnlyObjectWrapper<Piece>(Piece.BISHOP);
        board[1][0]= new ReadOnlyObjectWrapper<Piece>(Piece.ROOK);
        board[1][1]= new ReadOnlyObjectWrapper<Piece>(Piece.ROOK);
        board[1][2]= new ReadOnlyObjectWrapper<Piece>(Piece.EMPTY);
    }

    public ReadOnlyObjectProperty<Piece> pieceProperty(int i, int j){

        return board[i][j].getReadOnlyProperty();
    }

    public boolean canMove(int i, int j){
        int emptyRow = emptyRow();
        int emptyCol = emptyCol();
        if (board[i][j].get() == Piece.KING){

        }
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
        if (board[0][0].get()==Piece.BISHOP && board[0][1].get()==Piece.BISHOP && board[0][2].get()==Piece.EMPTY
            && board[1][0].get()==Piece.ROOK && board[1][1].get()==Piece.ROOK && board[1][2].get()==Piece.KING)
            return true;
        else
            return false;
    }
}
