package frontCode;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.*;

public class InfoPanel extends JPanel {

    //this is to implement singleton design pattern
    private static InfoPanel infoPanel;
    private static boolean isShowing;
    private JButton goBackIcon;
    private JLabel userNameLabel;
    private JLabel userIcon;
    private static String userName="user1";

    private InfoPanel() {
        setLayout(null);
        setLocation(-230,0);
        setBackground(Color.darkGray);
        addFields();
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
                    double progress = (double)duration / (double)RUN_TIME;
                    if (progress > 1f) {
                        progress = 1f;
                        ((Timer)e.getSource()).stop();
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
        value = (int)Math.round((double)distance * fraction);
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

    public static InfoPanel getInstance(){
        return Objects.requireNonNullElseGet(infoPanel, () -> infoPanel = new InfoPanel());
    }
    public void showPanel(){
        MenuFrame.getInstance().removeItemsButton();
        if(!isShowing) {
            Rectangle from = new Rectangle(-230, 0, 230, 707);
            Rectangle to = new Rectangle(0, 3, 230, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();
            isShowing=true;

        }
    }
    public void hidePanel(){
        if(isShowing) {
            MenuFrame.getInstance().addItemsButton();
            Rectangle from = new Rectangle(0, 3, 230, 707);
            Rectangle to = new Rectangle(-230, 0, 230, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();

            isShowing=false;
        }
    }
    public void addFields(){
        goBackIcon=new JButton(new ImageIcon("Documents/images/GoBackIcon.jpg"));
        goBackIcon.setLocation(25,35);
        goBackIcon.setSize(28,28);
        goBackIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanel();
            }
        });


        userIcon=new JLabel(new ImageIcon("Documents/images/UserIcon.jpg"));
        userIcon.setLocation(25,70);
        userIcon.setSize(30,30);

        userNameLabel=new JLabel(userName);
        userNameLabel.setSize(200,30);
        userNameLabel.setLocation(60,70);
        userNameLabel.setFont(new Font("Comic Sans MS", 10, 28));
        userNameLabel.setForeground(Color.white);

        add(userNameLabel);
        add(userIcon);
        add(goBackIcon);
    }

}