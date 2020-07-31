package graphic;

import javax.swing.*;
import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginFrame extends JFrame {
    JTextField userNameField;
    JTextField passwordField;
    JCheckBox checkBox;
    JButton signUpButton;
    public LoginFrame(){
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

        JLabel label=new JLabel("Remember me");
        label.setForeground(Color.white);
        label.setLocation(200,280);
        label.setSize(100,40);

        checkBox=new JCheckBox();
        checkBox.setSize(20,20);
        checkBox.setLocation(175,290);

        signUpButton=new JButton("Sign up");
        signUpButton.setLocation(363,300);
        signUpButton.setSize(90,20);
        signUpButton.setForeground(Color.darkGray);

        add(label);
        add(checkBox);
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

        public HintTextField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(this.getText().isEmpty()) {
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

}
