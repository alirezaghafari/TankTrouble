package menu.back;

import menu.front.SignUpFrame;
import tankGame.GameLauncher;

import javax.swing.*;

/*** In The Name of Allah ***/


public class Main {

    public static void main(String[] args) {

        //add look and feel
        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e){
        }

        //Show the game menu ...
        SignUpFrame.getInstance();

    }
}
