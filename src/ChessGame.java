import javax.swing.*;
import java.awt.*;

public class ChessGame extends JPanel {
    int state = 0; // 0 = starting up, 1 = start menu, 2 = game in progress, 3 = game finished

    TimerPanel timerPanel;
    GamePanel gamePanel;
    ContentPanel contentPanel;
    StartPanel startPanel;

    public static void main(String[] args) {
        createGUI();
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel chessGame = new ChessGame();
        frame.add(chessGame);

        frame.setLocation(500, 300);
        frame.pack();
        frame.setVisible(true);
    }

    public ChessGame() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        showStartMenu();

    }

    private void showStartMenu() {
        startPanel = new StartPanel(this);
        add(startPanel);
    }

    public void startGameClicked() {
        startGame();
    }

    private void startGame() {
        //startPanel.setVisible(false);
        remove(startPanel);
        startPanel = null;

        timerPanel = new TimerPanel();
        gamePanel = new GamePanel();
        contentPanel = new ContentPanel();
        timerPanel.gamePanel = gamePanel;

        add(contentPanel);
        add(gamePanel);
        add(timerPanel);

        state = 2;



        startGamePanels();
    }

    void startGamePanels() {
        timerPanel.startGame();
        gamePanel.startGame();

    }


}
