import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    public int id;


    JLabel img_label;
    Piece piece;
    boolean enPassantAble;

    public Tile(int id, Piece[] pieces) {
        super(null);
        this.id = id;

        if (((id % 8) % 2 == 0 && (id / 8) % 2 == 0) || ((id % 8) % 2 == 1 && (id / 8) % 2 == 1)) {
            setBackground(Color.green);
        } else {
            setBackground(Color.white);
        }

        ImageIcon img = null;
        switch (id) {
            case 0: case 7:
                //make black rooks
                img = new ImageIcon(getClass().getResource("pieces-basic-png/black-rook.png"));
                piece = new Rook(1, id);
                break;
            case 1: case 6:
                //make black knights
                img = new ImageIcon(getClass().getResource("pieces-basic-png/black-knight.png"));
                piece = new Knight(1, id);
                break;
            case 2: case 5:
                //make black bishop
                img = new ImageIcon(getClass().getResource("pieces-basic-png/black-bishop.png"));
                piece = new Bishop(1, id);
                break;
            case 3:
                //make black queen
                img = new ImageIcon(getClass().getResource("pieces-basic-png/black-queen.png"));
                piece = new Queen(1, id);
                break;
            case 4:
                //make black king
                img = new ImageIcon(getClass().getResource("pieces-basic-png/black-king.png"));
                piece = new King(1, id);
                break;
            case 56: case 63:
                //make white rooks
                img = new ImageIcon(getClass().getResource("pieces-basic-png/white-rook.png"));
                piece = new Rook(0, id);
                break;
            case 57: case 62:
                //make white knights
                img = new ImageIcon(getClass().getResource("pieces-basic-png/white-knight.png"));
                piece = new Knight(0, id);
                break;
            case 58: case 61:
                //make white bishop
                img = new ImageIcon(getClass().getResource("pieces-basic-png/white-bishop.png"));
                piece = new Bishop(0, id);
                break;
            case 59:
                //make white queen
                img = new ImageIcon(getClass().getResource("pieces-basic-png/white-queen.png"));
                piece = new Queen(0, id);
                break;
            case 60:
                //make white king
                img = new ImageIcon(getClass().getResource("pieces-basic-png/white-king.png"));
                piece = new King(0, id);
                break;
            default:
                //make empty
        }
        if (id >= 8 && id <= 15) {
            img = new ImageIcon(getClass().getResource("pieces-basic-png/black-pawn.png"));
            piece = new Pawn(1, id);
        }
        if (id >= 48 && id <= 55){
            img = new ImageIcon(getClass().getResource("pieces-basic-png/white-pawn.png"));
            piece = new Pawn(0, id);
        }

        if (img != null) {
            Image scaledImage = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            img_label = new JLabel(new ImageIcon(scaledImage));
            add(img_label);
            img_label.setBounds(5, 5, 50, 50);
        }
        if (id < 16) {
            pieces[id] = piece;
        } else if (id < 64 && id >47) {
            pieces[id-32] = piece;
        }


    }

}
