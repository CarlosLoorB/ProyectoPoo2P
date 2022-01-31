/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Clases.Crater;
import Clases.MainRover;
import DatosApp.CraterData;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author CAELOS JR 2018
 */
public class VistaPlanController implements Initializable {

    private TextField cratereEnRuta;
    @FXML
    private GridPane crateresRuta;
    @FXML
    private TextField crateresEnRuta;
    @FXML
    private ComboBox<MainRover> seleccionRover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    /**
     * Permite planificar la ruta mas cercana para recorrer los crateres
     * @param event 
     */
    @FXML
    private void buscarRuta(KeyEvent event) {
        MainRover roverSelec = seleccionRover.getValue();
        List<Crater> infoCrateres = CraterData.cargarCrater();
        String crateres = cratereEnRuta.getText();
        String [] strings = crateres.split(",");
        ArrayList<String> porBuscar = new ArrayList<String>(Arrays.asList(strings));
        ArrayList<String> orden = new ArrayList<String>(Arrays.asList(strings));
        
        
    }
    
}
