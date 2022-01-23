/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Clases.Crater;
import Clases.MainRover;
import DatosApp.CraterData;
import DatosApp.RoverData;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author CAELOS JR 2018
 */
public class VistaMapaController implements Initializable {

    @FXML
    private ComboBox<MainRover> pestanaRobot;
    @FXML
    private Pane paneMapa;
    @FXML
    private Pane paneMapa1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<MainRover> rovers = RoverData.cargarRovers();
       pestanaRobot.getItems().addAll(rovers);
       
       List<Crater> crateres = CraterData.cargarCrater();
       for(Crater crater: crateres){
           Circle c = new Circle(crater.getRadio(),Color.RED);
           StackPane st = new StackPane();
           st.getChildren().addAll(c);
           st.setLayoutX(crater.getLatitud());
           st.setLayoutY(crater.getLongitud());
           paneMapa1.getChildren().addAll(st);
       }    
    }    

    @FXML
    private void seleccionarRobot(ActionEvent event) {
    }
    
}
