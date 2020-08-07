package menu.front;

import menu.back.ClockDisplay;
import tankGame.GameEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class InfoPanel extends JPanel {

    //this is to implement singleton design pattern
    private static InfoPanel infoPanel;


    private static boolean isShowing;
    private static JButton goBackIcon;
    private static JLabel userNameLabel;
    private static JLabel userIcon;
    private static JLabel singleWinsAndLosses;
    private static JLabel multiWinsAndLosses;
    private static String userName = "user1";
    private static String timePlayed = "00:00:00";
    private static JButton logOutButton;
    private static JButton exitButton;
    private int multiWins = 0, multiLosses = 0, singleWins = 0, singleLosses = 0;
    private static JLabel timeLabel;



    private InfoPanel() {
        setLayout(null);
        setLocation(-220, 0);
        setBackground(Color.darkGray);
        addFields();
    }

    //set panel background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(new ImageIcon("Documents/images/menuIcons/OptionsPanelBackground.jpg").getImage(), 0, 0, null);
    }

    public static class Animate {

        private static final int RUN_TIME = 200;

        private JPanel panel;
        private Rectangle from;
        private Rectangle to;

        private long startTime;

        public Animate(JPanel panel, Rectangle from, Rectangle to) {
            this.panel = panel;
            this.from = from;
            this.to = to;
        }

        public void start() {
            Timer timer = new Timer(0, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long duration = System.currentTimeMillis() - startTime;
                    double progress = (double) duration / (double) RUN_TIME;
                    if (progress > 1f) {
                        progress = 1f;
                        ((Timer) e.getSource()).stop();
                    }
                    Rectangle target = calculateProgress(from, to, progress);
                    panel.setBounds(target);
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            startTime = System.currentTimeMillis();
            timer.start();
        }

    }

    private static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

        Rectangle bounds = new Rectangle();

        if (startBounds != null && targetBounds != null) {

            bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
            bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

        }

        return bounds;

    }

    public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

        Point point = new Point();

        if (startPoint != null && targetPoint != null) {

            point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
            point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

        }

        return point;

    }

    public static int calculateProgress(int startValue, int endValue, double fraction) {

        int value = 0;
        int distance = endValue - startValue;
        value = (int) Math.round((double) distance * fraction);
        value += startValue;

        return value;

    }

    public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

        Dimension size = new Dimension();

        if (startSize != null && targetSize != null) {

            size.width = calculateProgress(startSize.width, targetSize.width, progress);
            size.height = calculateProgress(startSize.height, targetSize.height, progress);

        }

        return size;

    }


    public void addFields() {
        goBackIcon = new JButton(new ImageIcon("Documents/images/menuIcons/GoBackIcon.jpg"));
        goBackIcon.setLocation(25, 35);
        goBackIcon.setSize(28, 28);
        goBackIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanel();
            }
        });


        userIcon = new JLabel(new ImageIcon("Documents/images/menuIcons/UserIcon.jpg"));
        userIcon.setLocation(25, 70);
        userIcon.setSize(30, 30);

        userNameLabel = new JLabel(userName);
        userNameLabel.setSize(200, 50);
        userNameLabel.setLocation(60, 60);
        userNameLabel.setFont(new Font("Comic Sans MS", 10, 28));
        userNameLabel.setForeground(Color.white);

        timeLabel = new JLabel("<html>Hours you have played:<br>&#160; &#160;&#160;&#160;" + timePlayed + "</html>");
        timeLabel.setFont(new Font("Comic Sans MS", 10, 22));
        timeLabel.setSize(200, 160);
        timeLabel.setLocation(25, 110);
        timeLabel.setForeground(Color.white);


        JLabel singlePlayerLabel = new JLabel("Single player");
        singlePlayerLabel.setFont(new Font("Comic Sans MS", 10, 24));
        singlePlayerLabel.setSize(200, 30);
        singlePlayerLabel.setLocation(25, 280);
        singlePlayerLabel.setForeground(Color.white);

        singleWinsAndLosses = new JLabel();



        JLabel multiPlayerLabel = new JLabel("Multi player");
        multiPlayerLabel.setFont(new Font("Comic Sans MS", 10, 24));
        multiPlayerLabel.setSize(200, 30);
        multiPlayerLabel.setLocation(25, 390);
        multiPlayerLabel.setForeground(Color.white);


        multiWinsAndLosses = new JLabel();

        reloadScorers();



        logOutButton = new JButton("Log Out");
        logOutButton.setFont(new Font("Comic Sans MS", 2, 16));
        logOutButton.setLocation(110, 660);
        logOutButton.setSize(95, 35);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanel();
                String[] options = {"Log Out", "Cancel"};
                if (JOptionPane.showOptionDialog(null, "Are you sure you want to log out?",
                        "Log Out",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Documents/images/menuIcons/UserIcon.jpg"), options, options[0]) == 0) {
                    MenuFrame.getInstance().hideFrame();
                    SignUpFrame.showFrame();
                }
            }
        });
        exitButton = new JButton(" Exit ");
        exitButton.setFont(new Font("Comic Sans MS", 2, 16));
        exitButton.setLocation(10, 660);
        exitButton.setSize(95, 35);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanel();
                String[] options = {"Exit", "Cancel"};
                if (JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
                        "Exit",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION, null, options, options[0]) == 0) {
                    System.exit(0);
                }
            }
        });



        add(userNameLabel);
        add(userIcon);
        add(goBackIcon);
        add(timeLabel);
        add(singlePlayerLabel);
        add(singleWinsAndLosses);
        add(multiPlayerLabel);
        add(multiWinsAndLosses);
        add(logOutButton);
        add(exitButton);

        hoursCounter();

    }

    public void showPanel() {
        if (!isShowing) {
            Rectangle from = new Rectangle(-220, 3, 220, 707);
            Rectangle to = new Rectangle(0, 3, 220, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();
            isShowing = true;
            MenuFrame.getInstance().removeItemsButton();
            reloadScorers();

        }
    }

    public void hidePanel() {
        if (isShowing) {
            MenuFrame.getInstance().addItemsButton();
            Rectangle from = new Rectangle(0, 3, 220, 707);
            Rectangle to = new Rectangle(-220, 3, 220, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();

            isShowing = false;
        }
    }

    public void hoursCounter() {
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                ClockDisplay clock=new ClockDisplay();
                while(true){
                    timePlayed=clock.getCurrentTime();
                    timeLabel.setText("<html>Hours you have played:<br>&#160; &#160;&#160;&#160;" + timePlayed + "</html>");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();


    }

    public static void setUserName(String userName) {
        InfoPanel.getInstance().userName = userName;
        InfoPanel.getInstance().userNameLabel.setText(userName);
    }

    public void reloadScorers(){
        singleWins= GameEngine.player2_score;
        singleLosses=GameEngine.player1_score;

        singleWinsAndLosses.setText("<html>Wins: " + singleWins + "<br>Losses: " + singleLosses + "</html>");
        singleWinsAndLosses.setFont(new Font("Comic Sans MS", 10, 18));
        singleWinsAndLosses.setSize(200, 60);
        singleWinsAndLosses.setLocation(65, 310);
        singleWinsAndLosses.setForeground(Color.white);

        multiWinsAndLosses.setText("<html>Wins: " + multiWins + "<br>Losses: " + multiLosses + "</html>");
        multiWinsAndLosses.setFont(new Font("Comic Sans MS", 10, 18));
        multiWinsAndLosses.setSize(200, 60);
        multiWinsAndLosses.setLocation(65, 420);
        multiWinsAndLosses.setForeground(Color.white);
    }

    public JLabel getUserNameLabel() {
        return userNameLabel;
    }

    public static void setTimePlayed(String timePlayed) {
        InfoPanel.timePlayed = timePlayed;
    }

    public static InfoPanel getInstance() {
        return Objects.requireNonNullElseGet(infoPanel, () -> infoPanel = new InfoPanel());
    }
}