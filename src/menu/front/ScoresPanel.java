package menu.front;

import tankGame.GameEngine;
import tankGame.GameLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a panel bottom of the game map
 * which shows the tanks icons and scores
 */
public class ScoresPanel extends JPanel {
    private static JLabel singleWinsAndLosses = new JLabel();
    private static JLabel computerWinsAndLosses = new JLabel();
    private static JProgressBar tank1stamina;
    private static JProgressBar tank2stamina;
    private static int singleWins = 0, singleLosses = 0;

    /**
     * the constructor
     */
    public ScoresPanel() {
        setLocation(0, 390);
        setSize(390, 100);
        setLayout(null);
        addScoresBar();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(new ImageIcon("src/scoresBarBackground.jpg").getImage(), 0, 0, null);
    }

    public void addScoresBar() {
        JLabel tank1Label = new JLabel(new ImageIcon("src/tankLabel1.png"));
        tank1Label.setLocation(278, 22);
        tank1Label.setSize(55, 50);

        JLabel tank2Label = new JLabel(new ImageIcon("src/tankLabel2.png"));
        tank2Label.setLocation(58, 22);
        tank2Label.setSize(50, 50);


        tank1stamina = new JProgressBar();
        tank1stamina.setMinimum(0);
        tank1stamina.setValue((int) OptionsPanel.getInstance().getTankStamina());
        tank1stamina.setMaximum((int) OptionsPanel.getInstance().getTankStamina());
        tank1stamina.setOrientation(1);
        tank1stamina.setSize(20, 80);
        tank1stamina.setLocation(350, 10);

        tank2stamina = new JProgressBar();
        tank2stamina.setValue((int) OptionsPanel.getInstance().getTankStamina());
        tank2stamina.setMinimum(0);
        tank2stamina.setMaximum((int) OptionsPanel.getInstance().getTankStamina());
        tank2stamina.setOrientation(1);
        tank2stamina.setSize(20, 80);
        tank2stamina.setLocation(20, 10);


        singleWinsAndLosses = new JLabel();
        reloadScorers();
        computerWinsAndLosses = new JLabel();
        reloadScorers();

        JButton endButton = new JButton("End");
        endButton.setSize(60, 20);
        endButton.setLocation(162, 72);
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLauncher.hideFrame();
                MenuFrame.showFrame();
            }
        });

        add(tank1stamina);
        add(tank2stamina);
        add(tank1Label);
        add(tank2Label);
        add(singleWinsAndLosses);
        add(computerWinsAndLosses);
        add(endButton);
    }

    /**
     * a method to reload the number of wins and losses
     */
    public static void reloadScorers() {
        singleWins = GameEngine.player2_score;
        singleLosses = GameEngine.player1_score;

        singleWinsAndLosses.setText("<html>" + singleWins + "<br><br>" + singleLosses + "</html>");
        singleWinsAndLosses.setFont(new Font("Comic Sans MS", 12, 18));
        singleWinsAndLosses.setSize(60, 100);
        singleWinsAndLosses.setLocation(115, 0);
        singleWinsAndLosses.setForeground(Color.white);


        computerWinsAndLosses.setText("<html>" + singleLosses + "<br><br>" + singleWins + "</html>");
        computerWinsAndLosses.setFont(new Font("Comic Sans MS", 12, 18));
        computerWinsAndLosses.setSize(60, 100);
        computerWinsAndLosses.setLocation(258, 0);
        computerWinsAndLosses.setForeground(Color.white);


    }

    /**
     * a method to reset tank stamina
     * @param playerNumber player 1 or player 2
     * @param stamina tank stamina
     */
    public static void reloadStamina(int playerNumber, int stamina) {
        if (playerNumber == 0)
            tank1stamina.setValue(stamina);
        else
            tank2stamina.setValue(stamina);
    }
}
