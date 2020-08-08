package menu.front;

import javax.swing.*;
import java.awt.*;

public class ScoresPanel extends JPanel {
    public ScoresPanel(){
        setLocation(0,390);
        setSize(390,100);
        setLayout(null);
        addScoresBar();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(new ImageIcon("src/scoresBarBackground.jpg").getImage(), 0, 0, null);
    }
    public void addScoresBar(){
        JLabel tank1Label = new JLabel(new ImageIcon("src/tankLabel1.png"));
        tank1Label.setLocation(295, 20);
        tank1Label.setSize(55, 50);

        JLabel tank2Label = new JLabel(new ImageIcon("src/tankLabel2.png"));
        tank2Label.setLocation(40, 20);
        tank2Label.setSize(55, 50);



        add(tank1Label);
        add(tank2Label);
    }
}
