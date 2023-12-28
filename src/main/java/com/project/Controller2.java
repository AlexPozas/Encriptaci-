package com.project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller2 implements Initializable {

    @FXML
    private Button button0, button1, button2, buttonWeekdays, buttonMonths, buttonAnimals, buttonBrands, buttonFXML;
    @FXML
    private Label choiceLabel;
    @FXML
    private AnchorPane container;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private VBox yPane = new VBox();

    String fruits[] = { "Apple", "Banana", "Cherry", "Grape", "Kiwi", "Lemon", "Lime", "Mango", "Orange", "Peach", "Pear", "Pineapple", "Plum", "Strawberry" };
    String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
    String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    String animals[] = { "Dog", "Cat", "Horse", "Cow", "Pig" };
    String brands[] = { "Audi", "BMW", "Citroen", "Fiat", "Ford", "Honda", "Hyundai", "Kia", "Mazda", "Mercedes", "Nissan", "Opel", "Peugeot", "Renault", "Seat", "Skoda", "Suzuki", "Toyota", "Volkswagen", "Volvo" };
    
    // Called when the FXML file is loaded
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String value = "Apple";
        choiceLabel.setText(value);

        choiceBox.getItems().addAll(fruits);
        choiceBox.setValue(value);
        choiceBox.setOnAction((event) -> {
            choiceLabel.setText(choiceBox.getSelectionModel().getSelectedItem());
        });
    }

    @FXML
    private void toView0(ActionEvent event) {
        UtilsViews.setView("View0");
    }
    
    @FXML
    private void toView1(ActionEvent event) {
        UtilsViews.setView("View1");
    }

    @FXML
    private void toView2(ActionEvent event) {
        UtilsViews.setView("View2");
    }

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }
    
    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void animateToView2(ActionEvent event) {
        UtilsViews.setViewAnimating("View2");
    }

    @FXML
    private void setWeekdays (ActionEvent event) {

        String value = "Monday";

        choiceBox.getItems().clear();
        choiceBox.getItems().addAll(weekdays);
        choiceBox.setValue(value);

        choiceLabel.setText(value);
    }

    @FXML
    private void setMonths (ActionEvent event) {

        String value = "January";

        choiceBox.getItems().clear();
        choiceBox.getItems().addAll(months);
        choiceBox.setValue(value);

        choiceLabel.setText(value);
    }

    @FXML
    private void setAnimals (ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        for (String name: animals) {
            list.add(name);
        }
        yPane.getChildren().clear();
        for (String s : list) {
            Label label = new Label(s);
            label.setStyle("-fx-border-color: black;");
            yPane.getChildren().add(label);
        }
    }

    @FXML
    private void setBrands (ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        for (String name: brands) {
            list.add(name);
        }

        yPane.getChildren().clear();
        for (String s : list) {
            Label label = new Label(s);
            label.setStyle("-fx-border-color: black;");
            yPane.getChildren().add(label);
        }
    }

    @FXML
    private void setFXML (ActionEvent event) throws Exception {
     
        // List of two strings with spices and animals
        String[][] list = new String[][] { 
            new String[] { "Mamals", "Dod", "black" }, 
            new String[] { "Mamals", "Cat", "grey" },
            new String[] { "Mamals", "Horse", "brown" },
            new String[] { "Mamals", "Cow", "white" },
            new String[] { "Mamals", "Pig", "pink" },
            new String[] { "Birds", "Pidgeon", "grey" },
            new String[] { "Birds", "Duck", "white" },
            new String[] { "Birds", "Eagle", "brown" },
            new String[] { "Birds", "Owl", "black" },
            new String[] { "Birds", "Parrot", "green" },
            new String[] { "Fish", "Goldfish", "orange" },
            new String[] { "Fish", "Shark", "grey" },
            new String[] { "Fish", "Tuna", "silver" },
            new String[] { "Fish", "Salmon", "pink" },
            new String[] { "Fish", "Cod", "white" },
            new String[] { "Reptiles", "Snake", "black" },
            new String[] { "Reptiles", "Lizard", "green" },
            new String[] { "Reptiles", "Turtle", "brown" },
            new String[] { "Reptiles", "Crocodile", "grey" },
            new String[] { "Reptiles", "Alligator", "green" },
            new String[] { "Amphibians", "Frog", "green" },
            new String[] { "Amphibians", "Toad", "brown" },
            new String[] { "Amphibians", "Salamander", "grey" },
            new String[] { "Amphibians", "Newt", "brown" },
            new String[] { "Amphibians", "Axolotl", "pink" }
        };

        URL resource = this.getClass().getResource("/assets/listItem.fxml");

        yPane.getChildren().clear();
        for (String[] listElement : list) {
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();
            
            itemController.setTitle(listElement[1]);
            itemController.setSubtitle(listElement[0]);
            itemController.setCircleColor(listElement[2]);

            yPane.getChildren().add(itemTemplate);
        }
    }
}