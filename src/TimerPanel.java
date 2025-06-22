import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel implements ActionListener {

    static final int TIMERPANELWIDTH = 200;
    static final int TIMERPANELHEIGHT = 480;

    int blackTimeInMillisec;
    int whiteTimeInMillisec;
    GamePanel gamePanel;
    Timer timer;
    JLabel whiteTimer;
    JLabel blackTimer;

    public TimerPanel() {
        super(null);
        setPreferredSize(new Dimension(TIMERPANELWIDTH, TIMERPANELHEIGHT));
        setBackground(Color.lightGray);
        setVisible(true);
        setBorder(BorderFactory.createEmptyBorder());


        //black panel
        JPanel blackTimerPanel = new JPanel(null);
        add(blackTimerPanel);
        blackTimerPanel.setBackground(Color.black);
        blackTimerPanel.setSize(new Dimension(TIMERPANELWIDTH / 2, TIMERPANELHEIGHT / 8));
        blackTimerPanel.setLocation(TIMERPANELWIDTH/ 4, TIMERPANELHEIGHT / 8 * 2 + TIMERPANELHEIGHT /16);

        blackTimer = new JLabel("10:00");
        blackTimerPanel.add(blackTimer);
        blackTimer.setSize(new Dimension(TIMERPANELWIDTH / 2, TIMERPANELHEIGHT / 8));
        blackTimer.setFont(new Font("Monospaced", Font.BOLD, 30));
        blackTimer.setLocation(5, 0);
        blackTimer.setForeground(Color.white);

        //white panel
        JPanel whiteTimerPanel = new JPanel(null);
        add(whiteTimerPanel);
        whiteTimerPanel.setBackground(Color.white);
        whiteTimerPanel.setSize(new Dimension(TIMERPANELWIDTH / 2, TIMERPANELHEIGHT / 8));
        whiteTimerPanel.setLocation(TIMERPANELWIDTH/ 4, TIMERPANELHEIGHT / 8 * 4 + TIMERPANELHEIGHT /16);

        whiteTimer = new JLabel("10:00");
        whiteTimerPanel.add(whiteTimer);
        whiteTimer.setSize(new Dimension(TIMERPANELWIDTH / 2, TIMERPANELHEIGHT / 8));
        whiteTimer.setFont(new Font("Monospaced", Font.BOLD, 30));
        whiteTimer.setLocation(5, 0);
        whiteTimer.setForeground(Color.black);

        // instantiate timer counts
        whiteTimeInMillisec = blackTimeInMillisec = 10 * 60 * 1000;







    }

    void startGame() {
        startTimer();
    }

    private static String parseTimer(int timeInSec) {
        String res = timeInSec / 60 <= 9 ? "0" + timeInSec / 60 : ""+ timeInSec / 60;
        res += ":";
        res += timeInSec % 60 <= 9 ? "0" + timeInSec % 60 : "" + timeInSec % 60;

        return res;

    }

    private void startTimer() {
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int color = gamePanel.turnColor;
        if (color == 0 || color == 4 || color == 5) {
            whiteTimeInMillisec -= 10;
            whiteTimer.setText(parseTimer(whiteTimeInMillisec / 1000));
        }
        if (color == 1 || color == 2 || color == 3) {
            blackTimeInMillisec -= 10;
            blackTimer.setText(parseTimer(blackTimeInMillisec / 1000));
        }
        if (color > 5) {
            timer.stop();
        }
    }
}
