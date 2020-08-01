package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MenuFrame extends JFrame {
    private String userName="user1";
    private static MenuFrame menuFrame;
    private MenuFrame(){
        super("TANK TROUBLE");
        setSize(1275,735);
        setResizable(false);
        setBackGround();
        addButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // put frame at center of screen
        setVisible(true);

    }
    public void setBackGround(){
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Documents/images/MenuBackground.jpg")));
    }
    public void addButtons(){

        JLabel userIcon=new JLabel(new ImageIcon("Documents/images/UserIcon.jpg"));
        userIcon.setLocation(25,70);
        userIcon.setSize(30,30);
        add(userIcon);

        Icon logoutIcon = new ImageIcon("Documents/images/LogOutIcon.jpg");
        JButton logOutButton=new JButton(logoutIcon);
        logOutButton.setLocation(1160,70);
        logOutButton.setSize(89,30);

        JLabel userNameLabel=new JLabel(userName);
        userNameLabel.setSize(200,30);
        userNameLabel.setLocation(60,70);
        userNameLabel.setFont(new Font("Comic Sans MS", 10, 28));
        userNameLabel.setForeground(Color.darkGray);
        add(userNameLabel);



        Icon singleIcon = new ImageIcon("Documents/images/SinglePlayerIcon.jpg");
        JButton singlePlayerButton=new JButton(singleIcon);
        singlePlayerButton.setLocation(290,600);
        singlePlayerButton.setSize(200,80);


        Icon multiIcon = new ImageIcon("Documents/images/MultiIcon.jpg");
        JButton multiPlayerButton=new JButton(multiIcon);
        multiPlayerButton.setLocation(545,600);
        multiPlayerButton.setSize(200,80);



        Icon optionsIcon = new ImageIcon("Documents/images/OptionsIcon.jpg");
        JButton optionsButton=new JButton(optionsIcon);
        optionsButton.setLocation(800,600);
        optionsButton.setSize(200,80);


        add(logOutButton);
        add(singlePlayerButton);
        add(multiPlayerButton);
        add(optionsButton);
    }

    public static MenuFrame getInstance(){
        if(menuFrame==null)
            return menuFrame=new MenuFrame();
        else
            return menuFrame;
    }


}

