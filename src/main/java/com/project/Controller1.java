package com.project;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.PublicKey;
public class Controller1 implements Initializable{

    @FXML
    public Button returnb;

    @FXML
    public Button encript;

    @FXML
    public TextField claup;

    @FXML
    public TextField arxiu;

    @FXML
    public TextField desti;

    String rutaArxiu;
    String rutaClau;
    String outDirectory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Boton volver atras
        returnb.setOnAction(event -> {
            UtilsViews.setViewAnimating("View0");
            Main.result = false;
        });
        
        //Clau publica
        FileChooser fc = new FileChooser();
        claup.setOnMouseClicked(e -> {
            File selCFile = fc.showOpenDialog(null);
            
            if (selCFile != null) {
                
                
                // Obtener la ruta del archivo seleccionado
                 rutaClau = selCFile.getAbsolutePath();
                
            }
            desti.requestFocus();
        });

        //Arxiu
        arxiu.setOnMouseClicked(e -> {
            File selFile = fc.showOpenDialog(null);

            if (selFile != null) {
               
                rutaArxiu = selFile.getAbsolutePath();
            
            }
            
            desti.requestFocus();
        });


        
        //Encriptar
        encript.setOnMouseClicked(e -> {
           Encrypt();
        });
    }
    

    
    void Encrypt(){
        if(claup.getText() != "" && arxiu.getText() != "" && desti.getText() != ""){
            try {
                outDirectory = System.getProperty("user.dir")+"/data/"+desti.getText();
                PublicKey publicKeyFile = loadPublicKey(rutaClau);
                String document = new String(Files.readAllBytes(Paths.get(rutaArxiu)));
                byte[] encryptedData = encryptData(document, publicKeyFile);
                Files.write(Paths.get(outDirectory), encryptedData);
                Main.result=true;
                UtilsViews.addView(getClass(), "ViewR", "/assets/view3.fxml");
                UtilsViews.setView("ViewR");
                System.out.println("Encriptacio exitosa. Archiu encriptat guardat a: " + outDirectory);
            } catch (Exception e) {
                
                UtilsViews.setView("ViewR");
                System.out.println("Error al guardar la encriptación");
                e.printStackTrace();
            }
        }
    }

    public static PublicKey loadPublicKey(String filename) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (PublicKey) ois.readObject();
        }
    }

    public static byte[] encryptData(String data, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data.getBytes());
    }
}