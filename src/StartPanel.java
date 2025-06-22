import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartPanel extends JPanel implements MouseListener {

    ChessGame mainGame;
    public StartPanel(ChessGame mainGame) {

        super(null);
        this.mainGame = mainGame;
        setPreferredSize(new Dimension(880, 480));
        setBackground(Color.blue);
        setBorder(BorderFactory.createEmptyBorder());
        JLabel charlie = new JLabel("Charlie Uland's Chess Game");
        charlie.setFont(new Font("Times New Roman", Font.ITALIC, 45));
        charlie.setForeground(Color.white);
        add(charlie);
        charlie.setBounds(200, 100, 600, 200);





        JLabel start = new JLabel("Start");
        add(start);
        start.setFont(new Font("Sans Serif", Font.BOLD, 50));
        start.setBounds(380, 300, 200, 75);
        addMouseListener(this);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        g.setColor(Color.orange);
        g.fillRect(340, 300, 200, 75);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() <= 540 && e.getX() >= 340 && e.getY() >= 300 && e.getY() <= 375) {
            mainGame.startGameClicked();
        }
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


