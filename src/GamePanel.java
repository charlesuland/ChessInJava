import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements MouseListener {
    Tile[] tiles;
    static final int GAMEPANELWIDTH = 480;
    static final int GAMEPANELHEIGHT = 480;
    int turnColor = -1;
    Piece[] pieces;
    ArrayList<Integer> loadedMoves;
    int selectedTileId;
    int enPassantId = -1;
    PromotionPanel promotionPanel;






    public GamePanel() {
        super(null);
        setPreferredSize(new Dimension(GAMEPANELWIDTH, GAMEPANELHEIGHT));
        setBackground(Color.BLACK);
        tiles = new Tile[64];
        setBorder(BorderFactory.createEmptyBorder());
        pieces = new Piece[32];
        generateTiles();
        addMouseListener(this);
    }

    public void generateTiles() {
        for(int i = 0; i < 64; i++) {

            tiles[i] = new Tile(i, pieces);
            int x = GAMEPANELWIDTH / 8 * (i % 8);
            int y = GAMEPANELHEIGHT / 8 * (i / 8);

            tiles[i].setMinimumSize(new Dimension(60, 60));
            tiles[i].setBounds(x, y, GAMEPANELWIDTH/8, GAMEPANELHEIGHT/8);
            add(tiles[i]);

        }
    }

    public void startGame() {
        turnColor = 0;

    }

    public void handleMove(Tile clickedTile) {
        //make a switch case for the different options and test them or whatever.
        if(turnColor >1 && turnColor < 6 && (clickedTile.id <= 7 || clickedTile.id >= 56)) {
            int pawnId = -1;
            int piecesIndex = -1;
            for (int i = 8; i < 24; i++) {
                if((pieces[i].id / 8 == 0 || pieces[i].id /8 == 7) && pieces[i] instanceof Pawn) {
                    pawnId = pieces[i].id;
                    piecesIndex = i;
                }
            }
            int id = clickedTile.id;
            int mod = clickedTile.id % 4;
            Piece promotionPiece = null;
            ImageIcon img = null;


            if (turnColor == 2 && id >= 60 && id <= 63) {
                if (mod == 0) {
                    promotionPiece = new Knight(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-knight.png"));
                } else if (mod == 1) {
                    promotionPiece = new Bishop(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-bishop.png"));
                } else if (mod == 2) {
                    promotionPiece = new Rook(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-rook.png"));
                } else {
                    promotionPiece = new Queen(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-queen.png"));
                }
            }
            if (turnColor == 3 && id >= 56 && id <= 59) {
                if (mod == 0) {
                    promotionPiece = new Knight(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-knight.png"));
                } else if (mod == 1) {
                    promotionPiece = new Bishop(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-bishop.png"));
                } else if (mod == 2) {
                    promotionPiece = new Rook(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-rook.png"));
                } else {
                    promotionPiece = new Queen(1, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/black-queen.png"));
                }
            }
            if (turnColor == 4 && id >= 4 && id <= 7) {
                if (mod == 0) {
                    promotionPiece = new Knight(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-knight.png"));
                } else if (mod == 1) {
                    promotionPiece = new Bishop(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-bishop.png"));
                } else if (mod == 2) {
                    promotionPiece = new Rook(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-rook.png"));
                } else {
                    promotionPiece = new Queen(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-queen.png"));
                }
            }
            if (turnColor == 5 && id >= 0 && id <= 3) {
                if (mod == 0) {
                    promotionPiece = new Knight(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-knight.png"));
                } else if (mod == 1) {
                    promotionPiece = new Bishop(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-bishop.png"));
                } else if (mod == 2) {
                    promotionPiece = new Rook(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-rook.png"));
                } else {
                    promotionPiece = new Queen(0, pawnId);
                    img = new ImageIcon(getClass().getResource("pieces-basic-png/white-queen.png"));
                }
            }
            tiles[pawnId].piece = promotionPiece;
            pieces[piecesIndex] = promotionPiece;

            Image scaledImage = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel img_label = new JLabel(new ImageIcon(scaledImage));
            tiles[pawnId].img_label = img_label;
            tiles[pawnId].removeAll();
            tiles[pawnId].add(img_label);
            tiles[pawnId].img_label.setBounds(5, 5, 50, 50);
            promotionPanel.setVisible(false);
            remove(promotionPanel);
            promotionPanel = null;
            turnColor = (turnColor == 2 || turnColor == 3) ? 0 : 1;
        }
        if (turnColor != 1 && turnColor != 0) return;
        if (clickedTile.piece == null) {
            if (loadedMoves != null && loadedMoves.contains(clickedTile.id)) {
                makeMove(clickedTile.id);
            }
        } else if (clickedTile.piece.color != turnColor) {
            if (loadedMoves != null && loadedMoves.contains(clickedTile.id)) {
                makeMove(clickedTile.id);
            }
        } else {
            selectedTileId = clickedTile.id;
            loadedMoves = possibleMoves(clickedTile.id);
            return;
            //highlightLoadedMoves();
        }
        // color is changing in make move
        int col = turnColor == 1 ? 0 : 1;
        boolean movesAvailable = isMovesAvailable(col);
        boolean inCheck = isInCheck(col);
        if (!movesAvailable && inCheck) {
            turnColor = 6;
        } else if (!movesAvailable) {
            turnColor = 7;
        }

    }


    public boolean isMovesAvailable(int color) {

        for(Piece p : pieces) {
            if(color != p.color && p.alive && !possibleMoves(p.id).isEmpty()) {
                return true;
            }
        }


        return false;
    }

    public void makeMove(int newId) {

        if(tiles[selectedTileId].piece instanceof Pawn && newId == enPassantId) {
            if (tiles[selectedTileId].piece.color == 1 && selectedTileId / 8 == 4) {
                tiles[newId - 8].piece.alive = false;
                tiles[newId - 8].remove(0);
                tiles[newId - 8].piece = null;
                tiles[newId - 8].repaint();
            }
            if (tiles[selectedTileId].piece.color == 0 && selectedTileId / 8 == 3) {
                tiles[newId + 8].piece.alive = false;
                tiles[newId + 8].remove(0);
                tiles[newId + 8].piece = null;
                tiles[newId + 8].repaint();
            }

        } else if (tiles[newId].piece != null) {
            tiles[newId].piece.alive = false;
            tiles[newId].remove(0);
        }


        if(enPassantId != -1) {

            tiles[enPassantId].enPassantAble = false;
            enPassantId = -1;
        }

        if(tiles[selectedTileId].piece instanceof Pawn) {
            ((Pawn) tiles[selectedTileId].piece).movedAlready = true;
            if(selectedTileId + 16 == newId || selectedTileId - 16 == newId) {

                enPassantId = tiles[selectedTileId].piece.color == 1 ? newId - 8 : newId + 8;
                tiles[enPassantId].enPassantAble = true;
            }
        }



        tiles[newId].img_label = tiles[selectedTileId].img_label;
        tiles[newId].add(tiles[selectedTileId].img_label);
        tiles[selectedTileId].img_label = null;
        tiles[newId].piece = tiles[selectedTileId].piece;
        tiles[selectedTileId].piece = null;
        tiles[newId].piece.id = newId;
        turnColor = turnColor == 0 ? 1 : 0;
        tiles[newId].repaint();
        tiles[selectedTileId].repaint();
        loadedMoves = null;

        //promotion inbound - must change into a queen, rook, bishop or knight
        if(tiles[newId].piece instanceof Pawn && (newId / 8 == 7 || newId / 8 == 0)) {
            //pause game
            //2 = black promotion on the left
            //3 = black promotion on the right
            //4 = white promotion on the left
            //5 = white promotion on the right
            turnColor = 2;
            promotionPanel = new PromotionPanel(tiles[newId].piece.color);
            add(promotionPanel);
            if(newId >= 0 && newId <= 3) {
                promotionPanel.setBounds(GAMEPANELWIDTH /2, 0, GAMEPANELWIDTH/2, GAMEPANELWIDTH / 8);
                turnColor = 4;
            } else if (newId >= 4 && newId <= 7) {
                promotionPanel.setBounds(0, 0, GAMEPANELWIDTH/2, GAMEPANELWIDTH / 8);
                turnColor = 5;
            } else if (newId >= 56 && newId <= 59) {
                promotionPanel.setBounds(GAMEPANELWIDTH /2, GAMEPANELHEIGHT / 8 * 7, GAMEPANELWIDTH/2, GAMEPANELWIDTH / 8);
                turnColor = 2;
            } else {
                promotionPanel.setBounds(0,GAMEPANELHEIGHT / 8 * 7, GAMEPANELWIDTH/2, GAMEPANELWIDTH / 8);
                turnColor = 3;
            }
            System.out.println("we up in this bitch");
        }
    }


    public ArrayList<Integer> possibleMoves(int id) {

            ArrayList<Integer> boardMoves = tiles[id].piece.possibleMoves(tiles);

            boardMoves.removeIf(i -> moveLeadsToCheck(id, i));
            return boardMoves;


    }


    private boolean moveLeadsToCheck(int id, int move) {
        int color = tiles[id].piece.color;

        Piece movingPiece = tiles[id].piece;
        if (movingPiece instanceof King) {
            movingPiece.id = move;
        }
        tiles[id].piece = null;
        Piece possibleOldPiece;
        if (movingPiece instanceof Pawn && move == enPassantId) {
            possibleOldPiece = color == 1 ? tiles[move - 8].piece : tiles[move + 8].piece;
            if (color == 1) tiles[move - 8].piece = null;
            if (color == 0) tiles[move + 8].piece = null;
        } else {
            possibleOldPiece = tiles[move].piece;
            tiles[move].piece = movingPiece;
        }



        ;


        boolean leadsToCheck = isInCheck(color);
        //check king diagonals for bishops and queens and pawns
        //+7 +9 -7 -9
        //check king straights for rooks
        //+1 -1 +8 -8
        //check kings eight spots for knights;



        tiles[id].piece = movingPiece;

        //wuh oh why did i use this line
        movingPiece.id = id;

        if (movingPiece instanceof Pawn && move == enPassantId) {

            if (color == 1) tiles[move - 8].piece = possibleOldPiece;
            if (color == 0) tiles[move + 8].piece = possibleOldPiece;
        } else {
            tiles[move].piece = possibleOldPiece;
            //tiles[id].piece = null;
        }




        return leadsToCheck;
    }

    private boolean isInCheck(int color) {
        int id = color == 0 ? pieces[28].id : pieces[4].id;
        //check king diagonals for bishops and queens and pawns
        //+7 +9 -7 -9
        //king can see thorugh things right now
        int idUpLeft = id - 9;
        while (idUpLeft >= 0 && idUpLeft % 8 != 7) {
            Piece tilePiece = tiles[idUpLeft].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Bishop || tilePiece instanceof Queen)) {
                return true;
            }
            if (tilePiece != null) break;
            idUpLeft -= 9;
        }
        int idUpRight = id - 7;
        while (idUpRight >= 1 && idUpRight % 8 != 0) {
            Piece tilePiece = tiles[idUpRight].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Bishop || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idUpRight -= 7;
            }
        }
        int idDownLeft = id + 7;
        while (idDownLeft <= 62 && idDownLeft % 8 != 7) {
            Piece tilePiece = tiles[idDownLeft].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Bishop || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idDownLeft += 7;
            }
        }
        int idDownRight = id + 9;
        while (idDownRight <= 63 && idDownRight % 8 != 0) {
            Piece tilePiece = tiles[idDownRight].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Bishop || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idDownRight += 9;
            }
        }

        //check king straights for rooks
        //+1 -1 +8 -8

        int idUp = id - 8;
        while (idUp >= 0) {
            Piece tilePiece = tiles[idUp].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Rook || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idUp -= 8;
            }
        }
        int idDown = id + 8;
        while (idDown <= 63) {
            Piece tilePiece = tiles[idDown].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Rook || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idDown += 8;
            }
        }
        int idLeft = id - 1;
        //idLeft >= 0 &&
        while (idLeft % 8 != 7 && idLeft >= 0) {
            Piece tilePiece = tiles[idLeft].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Rook || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idLeft -= 1;
            }
        }
        int idRight = id + 1;
        //idRight <= 63 &&
        while (idRight % 8 != 0) {
            Piece tilePiece = tiles[idRight].piece;
            if (tilePiece != null && tilePiece.color != color && (tilePiece instanceof Rook || tilePiece instanceof Queen)) {
                return true;
            } else {
                if (tilePiece != null) break;
                idRight += 1;
            }
        }

        //check kings eight spots for knights;
        if (id % 8 >= 2 && id / 8 >= 1) {
            if (tiles[id-10].piece != null && tiles[id-10].piece.color != color && tiles[id-10].piece instanceof Knight) {
                return true;
            }


        }
        if (id % 8 >= 1 && id / 8 >= 2) {
            //add -17
            if (tiles[id-17].piece != null && tiles[id-17].piece.color != color && tiles[id-17].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 >= 2 && id / 8 <= 6) {
            //add +6
            if (tiles[id+6].piece != null && tiles[id+6].piece.color != color && tiles[id+6].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 >= 1 && id / 8 <= 5) {
            //add 15
            if (tiles[id+15].piece != null && tiles[id+15].piece.color != color && tiles[id+15].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 <= 5 && id / 8 >= 1) {
            // add -6
            if (tiles[id-6].piece != null && tiles[id-6].piece.color != color && tiles[id-6].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 <= 6 && id / 8 >= 2) {
            //add -15
            if (tiles[id-15].piece != null && tiles[id-15].piece.color != color && tiles[id-15].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 <= 6 && id / 8 <= 5) {
            // add 17
            if (tiles[id+17].piece != null && tiles[id+17].piece.color != color && tiles[id+17].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 <= 5 && id / 8 <= 6) {
            // add 10
            if (tiles[id+10].piece != null && tiles[id+10].piece.color != color && tiles[id+10].piece instanceof Knight) {
                return true;
            }

        }
        if (id % 8 > 0) {
            if (color == 1 && tiles[id +7].piece != null && tiles[id + 7].piece.color != color && tiles[id + 7].piece instanceof Pawn) {
                return true;
            }
            if (color == 0 && tiles[id -9].piece != null && tiles[id - 9].piece.color != color && tiles[id - 9].piece instanceof Pawn) {
                return true;
            }
        }

        if (id % 8 < 7) {
            if (color == 1 && tiles[id + 9].piece != null && tiles[id + 9].piece.color != color && tiles[id + 9].piece instanceof Pawn) {
                return true;
            }
            if (color == 0 && tiles[id - 7].piece != null && tiles[id - 7].piece.color != color && tiles[id - 7].piece instanceof Pawn) {
                return true;
            }
        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        handleMove(tiles[e.getX() / 60 + e.getY() / 60 * 8]);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}

class PromotionPanel extends JPanel {
    PromotionPanel(int color) {
        setPreferredSize(new Dimension(GamePanel.GAMEPANELWIDTH / 2, GamePanel.GAMEPANELHEIGHT / 8));
        setBackground(Color.gray);
        ImageIcon rook;
        ImageIcon knight;
        ImageIcon bishop;
        ImageIcon queen;
        if (color == 1) {
            rook = new ImageIcon(getClass().getResource("pieces-basic-png/black-rook.png"));
            knight = new ImageIcon(getClass().getResource("pieces-basic-png/black-knight.png"));
            bishop = new ImageIcon(getClass().getResource("pieces-basic-png/black-bishop.png"));
            queen = new ImageIcon(getClass().getResource("pieces-basic-png/black-queen.png"));
        } else {
            rook = new ImageIcon(getClass().getResource("pieces-basic-png/white-rook.png"));
            knight = new ImageIcon(getClass().getResource("pieces-basic-png/white-knight.png"));
            bishop = new ImageIcon(getClass().getResource("pieces-basic-png/white-bishop.png"));
            queen = new ImageIcon(getClass().getResource("pieces-basic-png/white-queen.png"));
        }

        Image scaledKnight = knight.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel knightL = new JLabel(new ImageIcon(scaledKnight));
        add(knightL);
        knightL.setBounds(5, 5, 50, 50);
        Image scaledBishop = bishop.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel bishopL = new JLabel(new ImageIcon(scaledBishop));
        add(bishopL);
        bishopL.setBounds(65, 5, 50, 50);
        Image scaledRook = rook.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel rookL = new JLabel(new ImageIcon(scaledRook));
        add(rookL);
        rookL.setBounds(125, 5, 50, 50);
        Image scaledQueen = queen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel queenL = new JLabel(new ImageIcon(scaledQueen));
        add(queenL);
        queenL.setBounds(185, 5, 50, 50);
    }
}
