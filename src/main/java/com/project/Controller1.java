package com.project;

import java.net.URL;

import java.util.ResourceBundle;


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
import java.security.Key;
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


    public File clauPublica;
    public File arxiuAEncriptar;

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
                claup.setText(selCFile.getName());
                clauPublica = selCFile;
            }
            desti.requestFocus();
        });

        //Arxiu
        arxiu.setOnMouseClicked(e -> {
            File selFile = fc.showOpenDialog(null);

            if (selFile != null) {
                arxiu.setText(selFile.getName());
                arxiuAEncriptar = selFile;
            }

            desti.requestFocus();
        });
        
        //Encriptar
        encript.setOnMouseClicked(e -> {
            if (clauPublica != null && arxiuAEncriptar != null) {
                try {String outputPath = System.getProperty("user.dir")+"/data/"+desti.getText();
                    // Lógica de encriptación
                    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                    keyGen.init(128);
                    Key key = keyGen.generateKey();

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, key);

                    FileInputStream inputStream = new FileInputStream(arxiuAEncriptar);
                    CipherOutputStream outputStream = new CipherOutputStream(new FileOutputStream(outputPath), cipher);

                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    inputStream.close();
                    outputStream.close();
                    Main.result = true;
                    UtilsViews.addView(getClass(), "ViewR", "/assets/view3.fxml");
                    UtilsViews.setView("ViewR");
                    System.out.println("Encriptación exitosa");
                } catch (Exception r) {
                    r.printStackTrace();
                }
            } else {
                System.out.println("Por favor, seleccione la clave pública y el archivo a encriptar");
            }
        });
    }
    
    

}