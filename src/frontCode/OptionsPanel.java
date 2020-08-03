package frontCode;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

public class OptionsPanel extends JPanel {

    //this is to implement singleton design pattern
    private static OptionsPanel optionsPanel;
    private static boolean isShowing;
    private static JButton goBackIcon;

    private OptionsPanel() {
        setLayout(null);
        setLocation(1275,0);
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

    public static OptionsPanel getInstance(){
        return Objects.requireNonNullElseGet(optionsPanel, () -> optionsPanel = new OptionsPanel());
    }
    public void showPanel(){
        if(!isShowing) {
            Rectangle from = new Rectangle(1275, 3, 260, 707);
            Rectangle to = new Rectangle(1015, 3, 260, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();
            isShowing=true;

        }
    }
    public void hidePanel(){
        if(isShowing) {
            Rectangle from = new Rectangle(1015, 3, 260, 707);
            Rectangle to = new Rectangle(1275, 3, 260, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();

            isShowing=false;

        }
    }
    public void addFields(){
        goBackIcon=new JButton(new ImageIcon("Documents/images/GoBackIconReverse.jpg"));
        goBackIcon.setLocation(200,35);
        goBackIcon.setSize(28,28);
        goBackIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanel();
            }
        });





        add(goBackIcon);
    }

    public boolean isOpen(){
        return isShowing;
    }
}