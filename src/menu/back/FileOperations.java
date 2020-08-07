package menu.back;

import menu.front.OptionsPanel;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class FileOperations {
    //this is to implement singleton pattern
    private static FileOperations instance=null;

    private FileOperations(){}

    public boolean isPasswordSaved(String username){
        File userList=new File("Documents/LoginInfo/listOfUsers.txt");
        ArrayList<String> users=new ArrayList<>();
        if(userList.exists()) {
            try(FileReader fileReader=new FileReader(userList);
            BufferedReader reader=new BufferedReader(fileReader)) {
                while (reader.ready())
                    users.add(reader.readLine());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean flag=false;
        for(String st:users){
            String name=st.split("#")[0];
            String savePassword=st.split("#")[1];
            if(name.equals(username)&&savePassword.equalsIgnoreCase("true"))
                flag=true;
        }
        return flag;
    }
    public boolean signInCheck(String username,String password){
        String inputPassword="";
        String savedPassword="";
        try {
            String userPass=username+password;
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            byte[] userPassBytes=userPass.getBytes();
            byte[] hashBytes=messageDigest.digest(userPassBytes);
            DataInputStream reader=new DataInputStream(
                    new FileInputStream("Documents/LoginInfo/"+username+".bin")
            );
            byte[] bytes=new byte[reader.available()];
            reader.read(bytes);
            inputPassword=new String(hashBytes);
            savedPassword=new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputPassword.equals(savedPassword);
    }
    public boolean signUpCheck(String username,String password,boolean rememberMe){
        boolean canSignUp=true;
        File infoFile=new File("Documents/LoginInfo/"+username+".bin");
        if(infoFile.exists()) {
            canSignUp = false;
        }else{
            // to save a list of userNames
            try {
                FileWriter fileWriter=new FileWriter(new File("Documents/LoginInfo/listOfUsers.txt"),true);
                fileWriter.write(username+"#"+rememberMe+"\n");
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
                        new FileOutputStream(infoFile)
                );
                writer.write(hashBytes);
            } catch (NoSuchAlgorithmException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return canSignUp;
    }
    public static FileOperations getInstance() {
        return Objects.requireNonNullElseGet(instance, () -> instance = new FileOperations());
    }

}

