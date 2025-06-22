import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pawn extends Piece{
    boolean movedAlready = false;
    public Pawn(int color, int id) {
        super(color, id);
    }

    @Override
    public ArrayList<Integer> possibleMoves(Tile[] tiles) {
        ArrayList<Integer> moves = new ArrayList<>();
        int flag = color == 1 ? 1 : -1;
        if (tiles[id + 8 * flag].piece == null) {
            moves.add(id + 8 * flag);
        }
        if (!movedAlready && tiles[id + 16 * flag].piece == null && tiles[id + 8 * flag].piece == null) {
            moves.add(id + 16 * flag);
        }
        //need to account for left and right sides of board
        if(id % 8 != 0) {
            if(color == 1 && tiles[id + 7].piece != null && tiles[id + 7].piece.color != color) {
                moves.add(id + 7);
            }
            if(color == 0 && tiles[id - 9].piece != null && tiles[id - 9].piece.color != color) {
                moves.add(id - 9);
            }
            if(color == 1 && id / 8 == 4 && tiles[id + 7].enPassantAble) {
                moves.add(id + 7);
            }
            if(color == 0 && id / 8 == 3 && tiles[id - 9].enPassantAble) {
                moves.add(id - 9);
            }

        }
        if(id % 8 != 7) {
            if(color == 1 && tiles[id + 9].piece != null && tiles[id + 9].piece.color != color) {
                moves.add(id + 9);
            }
            if(color == 0 && tiles[id - 7].piece != null && tiles[id - 7].piece.color != color) {
                moves.add(id - 7);
            }
            if(color == 1 && id / 8 == 4 && tiles[id + 9].enPassantAble) {
                moves.add(id + 9);
            }
            if(color == 0 && id / 8 == 3 && tiles[id - 7].enPassantAble) {
                moves.add(id -7);
            }
        }


        return moves;
    }

}
