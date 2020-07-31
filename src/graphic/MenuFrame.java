package graphic;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    public MenuFrame(){
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


        add(singlePlayerButton);
        add(multiPlayerButton);
        add(optionsButton);
    }

}

