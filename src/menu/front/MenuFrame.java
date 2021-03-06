package menu.front;

import tankGame.GameLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The frame which contains start buttons and options
 * this is the main menu of game
 */
public class MenuFrame extends JFrame {
    private static JButton singlePlayerButton;
    private static JButton multiPlayerButton;
    private static JButton optionsButton;
    private static JButton itemsButton;


    //this is to implement singleton design pattern
    private static MenuFrame menuFrame;

    /**
     * the constructor
     */
    private MenuFrame() {
        super("TANK TROUBLE");
        setSize(1275, 735);
        setResizable(false);
        setBackGround();
        addButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // put frame at center of screen
        setVisible(true);

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent event) {
                if (event.isShiftDown()) {
                    if (event.getWheelRotation() > 0)
                        InfoPanel.getInstance().hidePanel();
                    else if (event.getWheelRotation() < 0) {
                        InfoPanel.getInstance().showPanel();
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() < 1015)
                    OptionsPanel.getInstance().hidePanel();
            }
        });

    }

    /**
     * to set image background for frame
     */
    public void setBackGround() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Documents/images/menuIcons/MenuBackground.jpg")));
    }

    /**
     * add three main buttons
     */
    public void addButtons() {

        itemsButton = new JButton(new ImageIcon("Documents/images/menuIcons/ItemsIcon.jpg"));
        itemsButton.setLocation(25, 70);
        itemsButton.setSize(27, 27);
        itemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel.getInstance().showPanel();
            }
        });


        Icon singleIcon = new ImageIcon("Documents/images/menuIcons/SinglePlayerIcon.jpg");
        singlePlayerButton = new JButton(singleIcon);
        singlePlayerButton.setLocation(290, 600);
        singlePlayerButton.setSize(200, 75);
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideFrame();
                GameLauncher.showFrame();
            }
        });


        Icon multiIcon = new ImageIcon("Documents/images/menuIcons/MultiIcon.jpg");
        multiPlayerButton = new JButton(multiIcon);
        multiPlayerButton.setLocation(545, 600);
        multiPlayerButton.setSize(200, 75);


        Icon optionsIcon = new ImageIcon("Documents/images/menuIcons/OptionsIcon.jpg");
        optionsButton = new JButton(optionsIcon);
        optionsButton.setLocation(800, 600);
        optionsButton.setSize(200, 75);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel.getInstance().hidePanel();
                if (OptionsPanel.getInstance().isOpen())
                    OptionsPanel.getInstance().hidePanel();
                else
                    OptionsPanel.getInstance().showPanel();
            }
        });


        addItemsButton();
        add(InfoPanel.getInstance());
        add(OptionsPanel.getInstance());
        add(singlePlayerButton);
        add(multiPlayerButton);
        add(optionsButton);

    }

    /**
     * set frame visible
     */
    public static void showFrame() {
        MenuFrame menuFrame = MenuFrame.getInstance();
        MenuFrame.getInstance().setLocationRelativeTo(null);
        menuFrame.setVisible(true);
        menuFrame.revalidate();
        menuFrame.repaint();

    }

    /**
     * set frame invisible
     */
    public static void hideFrame() {
        MenuFrame menuFrame = MenuFrame.getInstance();
        menuFrame.setVisible(false);

    }

    /**
     * set items to frame
     */
    public void addItemsButton() {
        add(itemsButton);
        revalidate();
        repaint();
    }


    public void removeItemsButton() {
        remove(itemsButton);
        revalidate();
        repaint();
    }


    /**
     * to implements singleton pattern
     * @return the instance of class
     */
    public static MenuFrame getInstance() {
        if (menuFrame == null)
            return menuFrame = new MenuFrame();
        else
            return menuFrame;
    }
}

