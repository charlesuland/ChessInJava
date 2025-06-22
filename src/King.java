import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class King extends Piece {


    public King(int color, int id) {
        super(color, id);

    }



    @Override
    public ArrayList<Integer> possibleMoves(Tile[] tiles) {
        ArrayList<Integer> moves = new ArrayList<>();
        //need to check the borders for the king
        if(id % 8 > 0 && id / 8 > 0) {
            if (tiles[id-9].piece == null || tiles[id-9].piece.color != this.color)
                moves.add(id - 9);
        }
        if(id / 8 > 0) {
            if (tiles[id-8].piece == null || tiles[id-8].piece.color != this.color)
                moves.add(id - 8);
        }
        if(id % 8 < 7 && id / 8 > 0) {
            if (tiles[id-7].piece == null || tiles[id-7].piece.color != this.color)
                moves.add(id - 7);
        }
        if(id % 8 > 0) {
            if (tiles[id-1].piece == null || tiles[id-1].piece.color != this.color)
                moves.add(id - 1);
        }
        if(id % 8 < 7) {
            if (tiles[id+1].piece == null || tiles[id+1].piece.color != this.color)
                moves.add(id + 1);
        }
        if(id % 8 > 0 && id / 8 < 7) {
            if (tiles[id+7].piece == null || tiles[id+7].piece.color != this.color)
                moves.add(id + 7);
        }
        if(id / 8 < 7) {
            if (tiles[id+8].piece == null || tiles[id+8].piece.color != this.color)
                moves.add(id + 8);
        }
        if(id % 8 < 7 && id / 8 < 7) {
            if (tiles[id+9].piece == null || tiles[id+9].piece.color != this.color)
                moves.add(id + 9);
        }

        return moves;
    }
}
