package menu.front;

import javax.swing.*;
import java.awt.*;

public class ScoresPanel extends JPanel {
    public ScoresPanel(){
        setLocation(0,390);
        setSize(390,60);
        addScoresBar();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(new ImageIcon("/scoresBarBackground.jpg").getImage(), 0, 0, null);
    }
    public void addScoresBar(){
        JLabel tank1Label=new JLabel(new ImageIcon("/tank1.png"));
        tank1Label.setLocation(50, 410);
        tank1Label.setSize(50, 50);




        add(tank1Label);
    }
}
