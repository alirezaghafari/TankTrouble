package menu.front;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Objects;


import javax.swing.*;

/**
 * a panel which can move over the menu frame
 * this panel has the sliders
 */
public class OptionsPanel extends JPanel {

    //this is to implement singleton design pattern
    private static OptionsPanel optionsPanel;

    private static boolean isShowing;


    private static JButton goBackIcon;
    private static JLabel tankStamina;
    private static JLabel fireRate;
    private static JLabel bulletSpeed;
    private static JSlider tankStaminaSlider;
    private static JSlider fireRateSlider;
    private static JSlider bulletSpeedSlider;

    /**
     * to set image background for panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(new ImageIcon("Documents/images/menuIcons/OptionsPanelBackground.jpg").getImage(), 0, 0, null);
    }

    /**
     * the constructor
     */
    private OptionsPanel() {
        setLayout(null);
        setLocation(1275, 0);
        addFields();
    }

    /**
     * to set animation for panel
     */
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
    /**
     * to set animation for panel
     */
    private static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

        Rectangle bounds = new Rectangle();

        if (startBounds != null && targetBounds != null) {

            bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
            bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

        }

        return bounds;

    }
    /**
     * to set animation for panel
     */
    public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

        Point point = new Point();

        if (startPoint != null && targetPoint != null) {

            point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
            point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

        }

        return point;

    }
    /**
     * to set animation for panel
     */
    public static int calculateProgress(int startValue, int endValue, double fraction) {

        int value = 0;
        int distance = endValue - startValue;
        value = (int) Math.round((double) distance * fraction);
        value += startValue;

        return value;

    }
    /**
     * to set animation for panel
     */
    public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

        Dimension size = new Dimension();

        if (startSize != null && targetSize != null) {

            size.width = calculateProgress(startSize.width, targetSize.width, progress);
            size.height = calculateProgress(startSize.height, targetSize.height, progress);

        }

        return size;

    }

    /**
     * to implements singleton pattern
     * @return the instance of class
     */
    public static OptionsPanel getInstance() {
        return Objects.requireNonNullElseGet(optionsPanel, () -> optionsPanel = new OptionsPanel());
    }

    /**
     * make panel visible
     */
    public void showPanel() {
        if (!isShowing) {
            Rectangle from = new Rectangle(1275, 3, 260, 707);
            Rectangle to = new Rectangle(1015, 3, 260, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();
            isShowing = true;

        }
    }

    /**
     * make panel invisible
     */
    public void hidePanel() {
        if (isShowing) {
            Rectangle from = new Rectangle(1015, 3, 260, 707);
            Rectangle to = new Rectangle(1275, 3, 260, 707);

            Animate animate = new Animate(this, from, to);
            animate.start();

            isShowing = false;

        }
    }

    /**
     * add sliders to the panel
     */
    public void addFields() {

        goBackIcon = new JButton(new ImageIcon("Documents/images/menuIcons/GoBackIconReverse.jpg"));
        goBackIcon.setLocation(200, 35);
        goBackIcon.setSize(28, 28);
        goBackIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanel();
            }
        });
        JLabel optionsLabel = new JLabel("OPTIONS");
        optionsLabel.setSize(200, 30);
        optionsLabel.setLocation(20, 60);
        optionsLabel.setFont(new Font("Comic Sans MS", 3, 30));
        optionsLabel.setForeground(Color.white);


        tankStamina = new JLabel("Tank stamina");
        tankStamina.setSize(200, 30);
        tankStamina.setLocation(20, 170);
        tankStamina.setFont(new Font("Comic Sans MS", 10, 24));
        tankStamina.setForeground(Color.white);


        fireRate = new JLabel("Fire rate");
        fireRate.setSize(200, 30);
        fireRate.setLocation(20, 300);
        fireRate.setFont(new Font("Comic Sans MS", 10, 24));
        fireRate.setForeground(Color.white);


        bulletSpeed = new JLabel("Bullet speed");
        bulletSpeed.setSize(200, 30);
        bulletSpeed.setLocation(20, 450);
        bulletSpeed.setFont(new Font("Comic Sans MS", 10, 24));
        bulletSpeed.setForeground(Color.white);

        tankStaminaSlider = new JSlider(40, 100, 70);
        fireRateSlider = new JSlider(10, 50, 30);
        bulletSpeedSlider = new JSlider(40, 100, 70);

        add(optionsLabel);
        addSliders(tankStaminaSlider, 30, 200, 40, 100);
        addSliders(fireRateSlider, 30, 330, 10, 50);
        addSliders(bulletSpeedSlider, 30, 470, 40, 100);
        add(goBackIcon);
        add(tankStamina);
        add(fireRate);
        add(bulletSpeed);
    }

    /**
     * add sliders to the panel
     * @param slider the JSlider
     * @param x xCoordinate
     * @param y yCoordinate
     * @param min min value
     * @param max max value
     */
    public void addSliders(JSlider slider, int x, int y, int min, int max) {
        slider.setLocation(x, y);
        slider.setSize(200, 70);
        slider.setForeground(Color.white);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        JLabel lowLabel = new JLabel("Low");
        lowLabel.setForeground(Color.white);
        labels.put(min, lowLabel);
        JLabel highLabel = new JLabel("High");
        highLabel.setForeground(Color.white);
        labels.put(max, highLabel);
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);


        add(slider);
    }

    /**
     * a method to find that the panel is showing or not
     * @return whether the panel is showing or not
     */
    public boolean isOpen() {
        return isShowing;
    }

    public double getTankStamina() {
        return tankStaminaSlider.getValue();
    }

    public double getFireRate() {
        return fireRateSlider.getValue();
    }

    public int getBulletSpeed() {
        return bulletSpeedSlider.getValue();
    }
}