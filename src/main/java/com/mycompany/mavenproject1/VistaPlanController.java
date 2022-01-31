/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Clases.Crater;
import Clases.MainRover;
import DatosApp.CraterData;
import DatosApp.RoverData;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author CAELOS JR 2018
 */
public class VistaPlanController implements Initializable {
    @FXML
    private TextField crateresEnRuta;
    @FXML
    private ComboBox<MainRover> seleccionRover;
    @FXML
    private VBox VboxPrincipal;
    @FXML
    private VBox VboxSec;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<MainRover> rovers = RoverData.cargarRovers();
       seleccionRover.getItems().addAll(rovers);

        // TODO
    }        
    /**
     * Permite planificar la ruta mas cercana para recorrer los crateres
     * @param event 
     */
    @FXML
    private void buscarRuta(KeyEvent event) {
        MainRover roverSelec = seleccionRover.getValue();
        if (event.getCode() == KeyCode.ENTER) {
            if(roverSelec == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Comando invalido");
                alert.setContentText("No se ha seleccionado rover");
                alert.showAndWait();
                crateresEnRuta.clear();
                return;
            }
            System.out.println(roverSelec);
            double distCorta = 10000000;
            Crater cratercerca = null;
            List<Crater> infoCrateres = CraterData.cargarCrater();
            String crateres = crateresEnRuta.getText();
            crateresEnRuta.clear();
            double pos = roverSelec.getUbicacion().getUbicacionX();
            double pos2 = roverSelec.getUbicacion().getUbicacionY();
            String[] strings = crateres.split(",");
            int longBusc = strings.length;
            ArrayList<Crater> porBuscar = new ArrayList<>();
            ArrayList<Crater> orden = new ArrayList<>();
            for (String s : strings) {
                System.out.println("las palabra" +s);
                for (Crater c : infoCrateres) {
                    System.out.println("los crateres "+ c.getNombre());
                    if (s.equals(c.getNombre())) {
                        System.out.println("la palabra "+s+" el crater "+c.getNombre());
                        porBuscar.add(c);
                    }
                }
            }
            while (porBuscar.size() > 0) {
                for (Crater c : porBuscar) {
                    System.out.println("Adentro del for elemento "+ c.getNombre());
                    double yFinal = c.getLongitud() - pos2;
                    double xFinal = c.getLatitud() - pos;
                    double distCortaPosible = Math.sqrt(Math.pow(xFinal, 2) + Math.pow(yFinal, 2));
                    if (distCortaPosible < distCorta) {
                        distCorta = distCortaPosible;
                        cratercerca = c;
                        System.out.println("Es el mas cercano "+cratercerca.getNombre());
                    }
                }
                System.out.println("salimos del for");
                orden.add(cratercerca);
                porBuscar.remove(cratercerca);
                pos = cratercerca.getLatitud();
                pos2 = cratercerca.getLongitud();
                distCorta = 10000000;
                cratercerca = null;
            }
            int contador = 1;
            VboxSec.getChildren().clear();
            GridPane grid = new GridPane();
            grid.add(new Label("Crateres Ordenados:"),0,0);
            grid.setGridLinesVisible(true);
            grid.setAlignment(Pos.CENTER);
            VboxSec.getChildren().add(grid);
            for (Crater c : orden) {
                
                grid.add(new Label(contador +".- "+c.getNombre()),0,contador);
                contador++;
            }
            if (orden.size() == 0 ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Comando invalido");
                alert.setContentText("No se puesto ningun crater valido");
                alert.showAndWait();
            }
            else if  (orden.size() < longBusc){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Comando invalido");
                alert.setContentText("Algunos valores de crater no fueron validos");
                alert.showAndWait();
            }
        }
        
    }

    @FXML
    private void Regresar(MouseEvent event) throws IOException {
        App.setRoot("VistaIncial");
    }

}
