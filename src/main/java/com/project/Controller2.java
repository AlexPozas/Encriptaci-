package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.PrivateKey;
import java.util.ResourceBundle;

import javax.crypto.Cipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;



import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;

public class Controller2 implements Initializable{

    @FXML
    public Button returnb;

    @FXML
    public Button desencript; 

    @FXML
    public TextField arxiu;

    @FXML
    public TextField cprivada;

    @FXML 
    public TextField contrasenya;

    @FXML
    public TextField desti;
    String con = "1234";

    String rutaArxiu;
    String rutaClau;
    String outDirectory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Retornar 
        returnb.setOnAction(event -> {
            UtilsViews.setViewAnimating("View0");
            Main.result = false;
        });

        FileChooser fc = new FileChooser();

        //Arxiu a desencriptar
        arxiu.setOnMouseClicked(e -> {
            
            File selFile = fc.showOpenDialog(null);

            if (selFile != null) {
               
                rutaArxiu = selFile.getAbsolutePath();
            } else {
                arxiu.setText("");
                
            }
            desti.requestFocus();
        });

        //ClauPrivada
        cprivada.setOnMouseClicked(e ->{

            File selFile = fc.showOpenDialog(null);

            if (selFile != null) {
                
                rutaClau = selFile.getAbsolutePath();
            } else {
                cprivada.setText("");
                
            }
            desti.requestFocus();
        });

        //Boton desencriptar
        desencript.setOnMouseClicked(e -> {
            Decrypt();
        });
        
    }
    private void Decrypt(){
        if(con.equals(contrasenya.getText())){
            try {
                outDirectory = System.getProperty("user.dir")+"/data/"+desti.getText();
                PrivateKey privateKeyFile = loadPrivateKey(rutaClau);
                byte[] encryptedData = Files.readAllBytes(Paths.get(rutaArxiu));
                byte[] decryptedData = decryptData(encryptedData, privateKeyFile);
                Files.write(Paths.get(outDirectory), decryptedData);
                Main.result=true;
                UtilsViews.addView(getClass(), "ViewR", "/assets/view3.fxml");
                UtilsViews.setView("ViewR");

                System.out.println("Dencriptacio exitosa. Archiu desencriptat guardat a: " + outDirectory);

            } catch (Exception e) {
                UtilsViews.setView("ViewR");
                e.printStackTrace();
            }
        }
    }

    public static PrivateKey loadPrivateKey(String filename) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (PrivateKey) ois.readObject();
        }
    }

    public static byte[] decryptData(byte[] data, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
    

}