package graphic;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SignInFrame extends JFrame {
    private JTextField userNameField;
    private JTextField passwordField;
    private JButton signUpButton;
    private JButton signInButton;
    private static SignInFrame signInFrame;
    private SignInFrame(){
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


        signInButton=new JButton("Sign in");
        signInButton.setLocation(363,300);
        signInButton.setSize(90,20);
        signInButton.setForeground(Color.darkGray);


        Icon signUpIcon = new ImageIcon("Documents/images/signUpBackground.jpg");
        signUpButton=new JButton(signUpIcon);
        signUpButton.setLocation(20,20);
        signUpButton.setSize(70,21);
        signUpButton.setForeground(Color.white);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInFrame.this.setVisible(false);
                SignUpFrame.getInstance().setVisible(true);
                userNameField.setText("  USERNAME:");
                passwordField.setText("  PASSWORD:");
            }
        });

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

    public static SignInFrame getInstance(){
        if(signInFrame==null)
            return signInFrame=new SignInFrame();
        else
            return signInFrame;
    }
}
