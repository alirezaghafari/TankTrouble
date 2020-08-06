package frontCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The frame which contains start buttons and options
 */
public class MenuFrame extends JFrame {
    private static JButton logOutButton;

    private static JButton singlePlayerButton;
    private static JButton multiPlayerButton;
    private static JButton optionsButton;
    private static JButton itemsButton;


    //this is to implement singleton design pattern
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

        this.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent event) {
                if (event.isShiftDown()) {
                    if(event.getWheelRotation()>0)
                        InfoPanel.getInstance().hidePanel();
                    else if(event.getWheelRotation()<0)
                        InfoPanel.getInstance().showPanel();

                }
            }
        });

    }
    public void setBackGround(){
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Documents/images/MenuBackground.jpg")));
    }
    public void addButtons(){

        itemsButton=new JButton(new ImageIcon("Documents/images/ItemsIcon.jpg"));
        itemsButton.setLocation(25,70);
        itemsButton.setSize(27,27);
        itemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel.getInstance().showPanel();
            }
        });


        Icon logoutIcon = new ImageIcon("Documents/images/LogOutIcon.jpg");
        logOutButton=new JButton(logoutIcon);
        logOutButton.setLocation(1160,70);
        logOutButton.setSize(89,30);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel.getInstance().hidePanel();
                String[] options = {"Log Out","Cancel"};
                if(JOptionPane.showOptionDialog(null, "Are you sure you want to log out?",
                        "Log Out",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Documents/images/UserIcon.jpg"), options, options[0])==0) {
                    hideFrame();
                    SignUpFrame.showFrame();
                }
            }
        });


        Icon singleIcon = new ImageIcon("Documents/images/SinglePlayerIcon.jpg");
        singlePlayerButton=new JButton(singleIcon);
        singlePlayerButton.setLocation(290,600);
        singlePlayerButton.setSize(200,80);


        Icon multiIcon = new ImageIcon("Documents/images/MultiIcon.jpg");
        multiPlayerButton=new JButton(multiIcon);
        multiPlayerButton.setLocation(545,600);
        multiPlayerButton.setSize(200,80);



        Icon optionsIcon = new ImageIcon("Documents/images/OptionsIcon.jpg");
        optionsButton=new JButton(optionsIcon);
        optionsButton.setLocation(800,600);
        optionsButton.setSize(200,80);



        addItemsButton();
        add(InfoPanel.getInstance());
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

    public static void showFrame(){
        MenuFrame menuFrame=MenuFrame.getInstance();
        MenuFrame.getInstance().setLocationRelativeTo(null);
        menuFrame.setVisible(true);
        menuFrame.revalidate();
        menuFrame.repaint();
    }
    public static void hideFrame(){
        MenuFrame menuFrame=MenuFrame.getInstance();
        menuFrame.setVisible(false);
    }

    public void addItemsButton(){
        add(itemsButton);
        revalidate();
        repaint();
    }
    public void removeItemsButton(){
        remove(itemsButton);
        revalidate();
        repaint();
    }




}
