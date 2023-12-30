package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

import javax.crypto.Cipher;



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

    File arxiuDesEncriptar;
    File clauPrivada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Retornar al menu
        returnb.setOnAction(event -> {
            UtilsViews.setViewAnimating("View0");
            Main.result = false;
        });

        FileChooser fc = new FileChooser();

        //Arxiu a desencriptar
        arxiu.setOnMouseClicked(e -> {
            
            File selFile = fc.showOpenDialog(null);

            if (selFile != null) {
                arxiu.setText(selFile.getName());
                arxiuDesEncriptar = selFile;
            } else {
                arxiu.setText("");
                arxiuDesEncriptar = null;
            }
            desti.requestFocus();
        });

        //ClauPrivada
        cprivada.setOnMouseClicked(e ->{

            File selFile = fc.showOpenDialog(null);

            if (selFile != null) {
                cprivada.setText(selFile.getName());
                clauPrivada = selFile;
            } else {
                cprivada.setText("");
                clauPrivada = null;
            }
            desti.requestFocus();
        });

        //Boton desencriptar
        desencript.setOnMouseClicked(e -> {
            if (arxiuDesEncriptar != null && clauPrivada != null) {
                try {
                    // Lógica de desencriptación
                    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                    keyGen.init(128);
                    Key key = keyGen.generateKey();

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, key);

                    FileInputStream inputStream = new FileInputStream(arxiuDesEncriptar);
                    CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

                    // Nombre y ruta del archivo desencriptado
                    String outputPath = System.getProperty("user.dir")+"/data/"+desti.getText();

                    FileOutputStream outputStream = new FileOutputStream(outputPath);

                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    cipherInputStream.close();
                    outputStream.close();

                    System.out.println("Desencriptación exitosa. Archivo desencriptado guardado en: " + outputPath);
                } catch (Exception r) {
                    r.printStackTrace();
                }
            } else {
                System.out.println("Por favor, seleccione el archivo y la clave privada para desencriptar.");
            }
            
        });
        
    }
    
    

}