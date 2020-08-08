package menu.front;

import menu.back.FileOperations;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class SignUpFrame extends JFrame {
    //this is to implement singleton design pattern
    private static SignUpFrame signUpFrame;


    private static JTextField userNameField;
    private static JPasswordField passwordField;
    private static JCheckBox checkBox;
    private static JButton signUpButton;
    private static JButton signInButton;
    private static JLabel rememberMe;
    private static JLabel warningLabel=new JLabel();


    private SignUpFrame() {
        super("Login");
        setSize(600, 370);
        setResizable(false);
        setBackground();
        addFields();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // put frame at center of screen
        setVisible(true);
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if ((!e.isShiftDown()) && e.getWheelRotation() > 2)
                    SignInPanel.getInstance().hidePanel();
            }
        });

    }


    public void setBackground() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Documents/images/menuIcons/LoginBackground.jpg")));
    }

    public void addFields() {
        userNameField = new HintTextField(" USERNAME:");
        userNameField.setLocation(178, 190);
        userNameField.setSize(250, 40);
        userNameField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        userNameField.setBackground(Color.gray);
        userNameField.setForeground(Color.white);

        passwordField = new HintPasswordField(" PASSWORD:");
        passwordField.setEchoChar((char)0);
        passwordField.setLocation(178, 240);
        passwordField.setSize(250, 40);
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        passwordField.setBackground(Color.gray);
        passwordField.setForeground(Color.white);


        checkBox = new JCheckBox();
        checkBox.setSize(20, 18);
        checkBox.setLocation(183, 291);

        rememberMe = new JLabel("Remember me");
        rememberMe.setForeground(Color.white);
        rememberMe.setLocation(203, 280);
        rememberMe.setSize(100, 40);
        rememberMe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (checkBox.isSelected())
                    checkBox.setSelected(false);
                else
                    checkBox.setSelected(true);

            }
        });


        signUpButton = new JButton("Sign up");
        signUpButton.setLocation(363, 300);
        signUpButton.setSize(90, 25);
        signUpButton.setForeground(Color.darkGray);
        signUpButton.addActionListener(e -> {
            boolean isInCorrectFormat= isInputInCorrectFormat(userNameField.getText())&& isInputInCorrectFormat(String.valueOf(passwordField.getPassword()));
            if(passwordField.getPassword().length<6||userNameField.getText().length()<6)
                addWarningLabel("Fields must have at least 6 characters!");
            else
                if(isInCorrectFormat) {
                    if (FileOperations.getInstance().signUpCheck(userNameField.getText(), String.valueOf(passwordField.getPassword()), checkBox.isSelected())) {
                        InfoPanel.getInstance().setUserName(userNameField.getText());
                        hideFrame();
                        MenuFrame.showFrame();
                    }else {
                        addWarningLabel("Username already taken!");
                    }
                }else {
                    addWarningLabel("Only a-z, 0-9, and underscores allowed!");
                }
        });


        Icon signIn = new ImageIcon("Documents/images/menuIcons/signInBackground.jpg");
        signInButton = new JButton(signIn);
        signInButton.setLocation(20, 20);
        signInButton.setSize(70, 25);
        signInButton.setForeground(Color.white);
        signInButton.addActionListener(e -> {
            SignInPanel.getInstance().showPanel();
        });


        warningLabel.setForeground(Color.RED);
        warningLabel.setSize(500,20);
        warningLabel.setFont(new Font("Arial", 10, 14));
        warningLabel.setLocation(183,320);


        addItems();
        add(SignInPanel.getInstance());

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
            if (this.getText().isEmpty() || this.getText().equals(" PASSWORD:") || this.getText().equals(" USERNAME:")) {
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
     * an inner class to put hint on password fields
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
            if (this.getText().isEmpty() || this.getText().equals(" PASSWORD:") || this.getText().equals(" USERNAME:")) {
                super.setText("");
                passwordField.setEchoChar('*');
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()||passwordField.getPassword().equals(" PASSWORD:")) {
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

    public static SignUpFrame getInstance() {
        if (signUpFrame == null)
            return signUpFrame = new SignUpFrame();
        else
            return signUpFrame;
    }



    public static void showFrame() {
        SignUpFrame signUpFrame = SignUpFrame.getInstance();
        SignUpFrame.getInstance().setLocationRelativeTo(null);
        SignUpFrame.getInstance().removeWarningLabel();
        userNameField.setText(" USERNAME:");
        passwordField.setText(" PASSWORD:");
        passwordField.setEchoChar((char)0);
        checkBox.setSelected(false);
        signUpFrame.setVisible(true);
        signUpFrame.revalidate();
        signUpFrame.repaint();
    }

    public  void hideFrame() {
        SignUpFrame signUpFrame = SignUpFrame.getInstance();
        signUpFrame.setVisible(false);
    }

    public void removeItems() {
        remove(userNameField);
        remove(passwordField);
        remove(checkBox);
        remove(rememberMe);
        remove(signUpButton);
        remove(signInButton);
    }

    public void addItems() {
        add(signInButton);
        add(userNameField);
        add(passwordField);
        add(signUpButton);
        add(checkBox);
        add(rememberMe);
        userNameField.setText(" USERNAME:");
        passwordField.setText(" PASSWORD:");
        checkBox.setSelected(false);
        revalidate();
        repaint();

    }
    public boolean isInputInCorrectFormat(String input){
        boolean isInCorrectFormat=true;
        for(char ch:input.toCharArray()){
            int asciiCode= ch;
            if(asciiCode>122||(asciiCode<97&&asciiCode>90)||(asciiCode<65&&asciiCode>57)||asciiCode<48)
                if(asciiCode!=45&&asciiCode!=95)
                    isInCorrectFormat=false;
        }
        return isInCorrectFormat;
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
