package com.project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Controller0 implements Initializable{
    @FXML
    public Button encriptar;

    @FXML
    public Button desencriptar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        encriptar.setOnAction(event -> {
            UtilsViews.setViewAnimating("View1");
        });

        desencriptar.setOnAction(event -> {
            UtilsViews.setViewAnimating("View2");
        });
    }

    

}