import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {

    int color;
    boolean alive = true;
    boolean lookingAtKing = false;
    boolean pinned = false;
    boolean attackingKing = false;
    int id;

    //int type; //0 =pawn, 1 = knight, 2 = bishop, 3 = knight, 4 = rook, 5 = queen, 6 = king

    public Piece(int color, int id) {
        this.color = color;
        this.id = id;
    }

    public abstract ArrayList<Integer> possibleMoves(Tile[] tiles);
}
