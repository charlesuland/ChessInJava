import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(int color, int id) {
        super(color, id);
    }

    @Override
    public ArrayList<Integer> possibleMoves(Tile[] tiles) {
        ArrayList<Integer> moves = new ArrayList<>();
        int idUp = id - 8;
        while (idUp >= 0) {
            if (tiles[idUp].piece == null) {
                moves.add(idUp);
                idUp -= 8;
                continue;
            }
            if (tiles[idUp].piece.color != tiles[id].piece.color) {
                moves.add(idUp);
            }
            break;
        }
        int idDown = id + 8;
        while (idDown <= 63) {
            if (tiles[idDown].piece == null) {
                moves.add(idDown);
                idDown += 8;
                continue;
            }
            if (tiles[idDown].piece.color != tiles[id].piece.color) {
                moves.add(idDown);
            }
            break;
        }
        int idLeft = id - 1;
        //idLeft >= 0 &&
        while (idLeft % 8 != 7 && idLeft >= 0) {
            if (tiles[idLeft].piece == null) {
                moves.add(idLeft);
                idLeft -= 1;
                continue;
            }
            if (tiles[idLeft].piece.color != tiles[id].piece.color) {
                moves.add(idLeft);
            }
            break;
        }
        int idRight = id + 1;
        //idRight <= 63 &&
        while (idRight % 8 != 0) {
            if (tiles[idRight].piece == null) {
                moves.add(idRight);
                idRight += 1;
                continue;
            }
            if (tiles[idRight].piece.color != tiles[id].piece.color) {
                moves.add(idRight);
            }
            break;
        }
        return moves;
    }
}
