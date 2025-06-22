import java.util.ArrayList;

public class Bishop extends Piece{
    boolean lookingAtKing = false;
    public Bishop(int color, int id) {
        super(color, id);
    }


    @Override
    public ArrayList<Integer> possibleMoves(Tile[] tiles) {
        int idUpLeft = id - 9;
        ArrayList<Integer> moves = new ArrayList<>();
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
        while (idDownRight <= 63 && idDownRight % 8 !=0) {
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
        return moves;
    }

    public void movePiece() {

    }
}
