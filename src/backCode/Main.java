package backCode; /*** In The Name of Allah ***/

import graphic.*;

import java.awt.*;
import javax.swing.*;

/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {

    public static void main(String[] args) {
        // Initialize the global thread-pool
        ThreadPool.init();


        // Show the game menu ...
        MenuFrame fr=MenuFrame.getInstance();
         new LoginFrame();




        // After the player clicks 'PLAY' ...
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame("Simple Ball !");
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                game.init();
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });

    }
}
