import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(int color, int id) {
        super(color, id);
    }

    public ArrayList<Integer> possibleMoves(Tile[] tiles) {
        //maybe change things to a linked list so things can be removed easier
        ArrayList<Integer> moves = new ArrayList<>();

        //knight has 8 moves
        // L shaped
        //need to check tiles at -17, -10, +6, +15, -6, -15, +17, +10

        if (id % 8 >= 2 && id / 8 >= 1) {
            if (tiles[id-10].piece == null || tiles[id-10].piece.color != this.color) {
                moves.add(id -10);
            }


        }
        if (id % 8 >= 1 && id / 8 >= 2) {
            //add -17
            if (tiles[id-17].piece == null || tiles[id-17].piece.color != this.color)
                moves.add(id -17);

        }
        if (id % 8 >= 2 && id / 8 <= 6) {
            //add +6
            if (tiles[id+6].piece == null || tiles[id+6].piece.color != this.color)
                moves.add(id + 6);

        }
        if (id % 8 >= 1 && id / 8 <= 5) {
            //add 15
            if (tiles[id+15].piece == null || tiles[id+15].piece.color != this.color)
                moves.add(id +15);

        }
        if (id % 8 <= 5 && id / 8 >= 1) {
            // add -6
            if (tiles[id-6].piece == null || tiles[id-6].piece.color != this.color)
                moves.add(id - 6);

        }
        if (id % 8 <= 6 && id / 8 >= 2) {
            //add -15
            if (tiles[id-15].piece == null || tiles[id-15].piece.color != this.color)
                moves.add(id - 15);

        }
        if (id % 8 <= 6 && id / 8 <= 5) {
            // add 17
            if (tiles[id+17].piece == null || tiles[id+17].piece.color != this.color)
                moves.add(id +17);

        }
        if (id % 8 <= 5 && id / 8 <= 6) {
            // add 10
            if (tiles[id+10].piece == null || tiles[id+10].piece.color != this.color)
                moves.add(id +10);

        }
        return moves;

    }
}
