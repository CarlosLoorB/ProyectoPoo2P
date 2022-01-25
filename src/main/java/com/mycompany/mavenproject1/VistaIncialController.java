/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author CAELOS JR 2018
 */
public class VistaIncialController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void botonExplorar(MouseEvent event) 
            throws IOException {
        App.setRoot("VistaMapa");
        
    }

    @FXML
    private void botonPlanificarRutas(MouseEvent event) {
    }

    @FXML
    private void botonVerReportes(MouseEvent event)throws IOException {
        
        App.setRoot("VistaReporte");
    }

    @FXML
    private void botonSalir(MouseEvent event) {
        Platform.exit();
    }
    
}
