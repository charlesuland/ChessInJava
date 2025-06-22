import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(int color, int id) {
        super(color, id);
    }


    public ArrayList<Integer> possibleMoves(Tile[] tiles) {
        ArrayList<Integer> moves = new ArrayList<>();
        int idUpLeft = id - 9;
        while (idUpLeft >= 0 && idUpLeft % 8 != 7) {
            if (tiles[idUpLeft].piece == null) {
                moves.add(idUpLeft);
                idUpLeft -= 9;
                continue;
            }
            if (tiles[idUpLeft].piece.color != tiles[id].piece.color) {
                moves.add(idUpLeft);
            }
            break;
        }

        int idUpRight = id - 7;
        while (idUpRight >= 1 && idUpRight % 8 != 0) {
            if (tiles[idUpRight].piece == null) {
                moves.add(idUpRight);
                idUpRight -= 7;
                continue;
            }
            if (tiles[idUpRight].piece.color != tiles[id].piece.color) {
                moves.add(idUpRight);
            }
            break;
        }
        int idDownRight = id + 9;
        while (idDownRight <= 63 && idDownRight % 8 != 0) {
            if (tiles[idDownRight].piece == null) {
                moves.add(idDownRight);
                idDownRight += 9;
                continue;
            }
            if (tiles[idDownRight].piece.color != tiles[id].piece.color) {
                moves.add(idDownRight);
            }
            break;
        }
        int idDownLeft = id + 7;
        while (idDownLeft <= 62 && idDownLeft % 8 != 7) {
            if (tiles[idDownLeft].piece == null) {
                moves.add(idDownLeft);
                idDownLeft += 7;
                continue;
            }
            if (tiles[idDownLeft].piece.color != tiles[id].piece.color) {
                moves.add(idDownLeft);
            }
            break;
        }
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
