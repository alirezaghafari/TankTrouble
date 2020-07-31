package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
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
        

    }
}
