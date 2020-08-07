package menu.front;


import menu.back.FileOperations;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class SignInPanel extends JPanel {

    //this is to implement singleton design pattern
    private static SignInPanel signInPanel;

    private static JTextField userNameField;
    private static JPasswordField passwordField;
    private static JButton signUpButton;
    private static JButton signInButton;
    private static boolean isPasswordSaved=false;
    private static JLabel warningLabel=new JLabel();


    private static boolean isShowing;


    private SignInPanel() {
        setLayout(null);
        setLocation(0, -347);
        addFields();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(new ImageIcon("Documents/images/menuIcons/LoginBackground2.jpg").getImage(), 0, 0, null);
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

    public static SignInPanel getInstance() {
        return Objects.requireNonNullElseGet(signInPanel, () -> signInPanel = new SignInPanel());
    }

    public void showPanel() {
        if (!isShowing) {
            removeWarningLabel();
            userNameField.setText("  USERNAME:");
            passwordField.setText("  PASSWORD:");
            passwordField.setEchoChar((char)0);
            signInPanel.revalidate();
            signInPanel.repaint();
            Rectangle from = new Rectangle(20, -347, 560, 347);
            Rectangle to = new Rectangle(20, -10, 560, 347);
            Animate animate = new Animate(this, from, to);
            animate.start();
            isShowing = true;

            SignUpFrame.getInstance().removeItems();
        }
    }

    public void hidePanel() {
        if (isShowing) {
            SignUpFrame.getInstance().addItems();
            Rectangle from = new Rectangle(20, -10, 560, 347);
            Rectangle to = new Rectangle(20, -347, 560, 347);

            Animate animate = new Animate(this, from, to);
            animate.start();

            isShowing = false;

        }
    }

    public void addFields() {
        userNameField = new HintTextField("  USERNAME:");
        userNameField.setLocation(153, 200);
        userNameField.setSize(250, 40);
        userNameField.setBackground(Color.gray);
        userNameField.setForeground(Color.white);


        passwordField = new HintPasswordField("  PASSWORD:");
        passwordField.setEchoChar((char)0);
        passwordField.setSize(250, 40);
        passwordField.setLocation(153, 250);
        passwordField.setBackground(Color.gray);
        passwordField.setForeground(Color.white);
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                isPasswordSaved=FileOperations.getInstance().isPasswordSaved(userNameField.getText());
                if(isPasswordSaved)
                    passwordField.setText("myPassword");
            }
        });

        signInButton = new JButton("Sign in");
        signInButton.setLocation(341, 300);
        signInButton.setSize(90, 25);
        signInButton.setForeground(Color.darkGray);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FileOperations.getInstance().isPasswordSaved(userNameField.getText())) {
                    InfoPanel.getInstance().setUserName(userNameField.getText());
                    hidePanel();
                    SignUpFrame.getInstance().hideFrame();
                    MenuFrame.showFrame();
                    isPasswordSaved=false;
                }
                else {
                    if (FileOperations.getInstance().signInCheck(userNameField.getText(), String.valueOf(passwordField.getPassword()))) {
                        InfoPanel.getInstance().setUserName(userNameField.getText());
                        hidePanel();
                        SignUpFrame.getInstance().hideFrame();
                        MenuFrame.showFrame();
                    } else {
                        addWarningLabel("Incorrect password or username!");
                    }
                }
            }
        });


        Icon signUpIcon = new ImageIcon("Documents/images/menuIcons/signUpBackground.jpg");
        signUpButton = new JButton(signUpIcon);
        signUpButton.setLocation(10, 33);
        signUpButton.setSize(70, 25);
        signUpButton.setForeground(Color.white);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpFrame.showFrame();
                hidePanel();
            }
        });

        warningLabel.setForeground(Color.RED);
        warningLabel.setSize(500,20);
        warningLabel.setFont(new Font("Arial", 10, 14));
        warningLabel.setLocation(170,323);


        add(signInButton);
        add(userNameField);
        add(passwordField);
        add(signUpButton);
    }

    /**
     * an inner class to put hint on text fields
     */
    class HintTextField extends JTextField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextField(String hint) {
            super.setText(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText().isEmpty() || this.getText().equals("  PASSWORD:") || this.getText().equals("  USERNAME:")) {
                super.setText("");
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    /**
     * an inner class to put hint on text fields
     */
    class HintPasswordField extends JPasswordField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintPasswordField(String hint) {
            super.setText(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText().isEmpty() || this.getText().equals("  PASSWORD:") || this.getText().equals("  USERNAME:")) {
                super.setText("");
                passwordField.setEchoChar('*');
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()||passwordField.getPassword().equals("  PASSWORD:")) {
                super.setText(hint);
                passwordField.setEchoChar((char)0);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    public void addWarningLabel(String message){
        removeWarningLabel();
        warningLabel.setText(message);
        add(warningLabel);
        revalidate();
        repaint();
    }
    public void removeWarningLabel(){
        remove(warningLabel);
        revalidate();
        repaint();
    }
}