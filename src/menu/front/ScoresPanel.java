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
    }
}
