package frontCode;

import javax.swing.*;
import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpFrame extends JFrame {
    private static JTextField userNameField;
    private static JTextField passwordField;
    private static JCheckBox checkBox;
    private static JButton signUpButton;
    private static JButton signInButton;

    //this is to implement singleton design pattern
    private static SignUpFrame signUpFrame;

    private SignUpFrame(){
        super("Login");
        setSize(600,360);
        setResizable(false);
        setBackground();
        addFields();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // put frame at center of screen
        setVisible(true);

    }


    public void setBackground() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Documents/images/LoginBackground.jpg")));
    }
    public void addFields(){
        userNameField=new HintTextField("  USERNAME:");
        userNameField.setSize(250,40);
        userNameField.setLocation(175,190);
        userNameField.setBackground(Color.gray);
        userNameField.setForeground(Color.white);

        passwordField=new HintTextField("  PASSWORD:");
        passwordField.setSize(250,40);
        passwordField.setLocation(175,240);
        passwordField.setBackground(Color.gray);
        passwordField.setForeground(Color.white);

        checkBox= new JCheckBox();
        checkBox.setSize(22,22);
        checkBox.setLocation(175,290);

        JLabel rememberMe=new JLabel("Remember me");
        rememberMe.setForeground(Color.white);
        rememberMe.setLocation(200,280);
        rememberMe.setSize(100,40);
        rememberMe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (checkBox.isSelected())
                    checkBox.setSelected(false);
                else
                    checkBox.setSelected(true);

            }
        });


        signUpButton=new JButton("Sign up");
        signUpButton.setLocation(363,300);
        signUpButton.setSize(90,20);
        signUpButton.setForeground(Color.darkGray);
        signUpButton.addActionListener(e -> {
            if(true){
                hideFrame();
                MenuFrame.showFrame();
            }
        });


        Icon signIn = new ImageIcon("Documents/images/signInBackground.jpg");
        signInButton=new JButton(signIn);
        signInButton.setLocation(20,20);
        signInButton.setSize(70,21);
        signInButton.setForeground(Color.white);
        signInButton.addActionListener(e -> {
            hideFrame();
            SignInFrame.showFrame();
        });

        add(signInButton);
        add(userNameField);
        add(passwordField);
        add(signUpButton);
        add(checkBox);
        add(rememberMe);

    }


    /**
     * an inner class to put hint on text fields
     */
    class HintTextField extends JTextField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextField( String hint) {
            super.setText(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(this.getText().isEmpty()||this.getText().equals("  PASSWORD:")||this.getText().equals("  USERNAME:")) {
                super.setText("");
                showingHint = false;
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if(this.getText().isEmpty()) {
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    public static SignUpFrame getInstance(){
        if(signUpFrame==null)
            return signUpFrame=new SignUpFrame();
        else
            return signUpFrame;
    }

    public static void showFrame(){
        SignUpFrame signUpFrame=SignUpFrame.getInstance();
        SignUpFrame.getInstance().setLocationRelativeTo(null);
        userNameField.setText("  USERNAME:");
        passwordField.setText("  PASSWORD:");
        checkBox.setSelected(false);
        signUpFrame.setVisible(true);
        signUpFrame.revalidate();
        signUpFrame.repaint();
    }
    public static void hideFrame(){
        SignUpFrame signUpFrame=SignUpFrame.getInstance();
        signUpFrame.setVisible(false);
    }
}
