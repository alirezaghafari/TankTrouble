package menu.back;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {
    public boolean signIn(String username,String password){
        String input="";
        String saved="";
        try {
            String userPass=username+password;
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            byte[] userPassBytes=userPass.getBytes();
            byte[] hashBytes=messageDigest.digest(userPassBytes);
            DataInputStream reader=new DataInputStream(
                    new FileInputStream("documents/LoginInfo/"+username+".bin")
            );
            byte[] bytes=new byte[reader.available()];
            reader.read(bytes);
            input=new String(hashBytes);
            saved=new String(bytes);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return input.equals(saved);
    }
    public void signUp(String username,String password){
        // to save a list of userNames
        try {
            FileWriter fileWriter=new FileWriter(new File("documents/LoginInfo/listOfUsers.txt"),true);
            fileWriter.write(username+"\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String userPass=username+password;
        MessageDigest messageDigest= null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] userPassBytes=userPass.getBytes();
            byte[] hashBytes=messageDigest.digest(userPassBytes);
            DataOutputStream writer=new DataOutputStream(
                    new FileOutputStream("documents/LoginInfo/"+username+".bin")
            );
            writer.write(hashBytes);
        } catch (NoSuchAlgorithmException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

