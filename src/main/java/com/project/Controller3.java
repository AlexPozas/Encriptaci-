package com.project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller3 implements Initializable{

    @FXML
    public Button returnb;

    @FXML
    public Label resultat;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        returnb.setOnMouseClicked(e ->{
            Main.result = false;
            UtilsViews.setView("View0");
            UtilsViews.controllers.remove(UtilsViews.controllers.size()-1);
        });

        if (Main.result) {
            resultat.setText("Operacio realitzada correctament");
        } else {
            resultat.setText("A ocurregut un error");
        }
    }
    
}