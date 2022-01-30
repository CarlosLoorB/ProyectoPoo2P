/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import DatosApp.CraterSensadoData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class VistaReporteController implements Initializable {

    @FXML
    private TextField txtFechaInicio;
    @FXML
    private TextField txtFechaFin;
    @FXML
    private TextField txtMaterial;
    @FXML
    private TableView<?> tbvTabla;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        
    }    

    @FXML
    private void filtrarFecha(KeyEvent event) {
        Comparator<CraterSensadoData> cmp = ((CraterSensadoData csd1, CraterSensadoData csd2)->{
            if(csd1.getFecha().isBefore(csd2.getFecha()))return -1;
            if(csd1.getFecha().isBefore(csd2.getFecha())) return 1;
            return 0;
            
        });
        
        if(txtFechaInicio.getText()!=null && txtFechaFin.getText() != null){
            List<CraterSensadoData> craterFechaInicioFin = new ArrayList<>();
            if(event.getCode() == KeyCode.ENTER){
                try{
                    for(CraterSensadoData csd : CraterSensadoData.leerCratersSensados()){
                        if(csd.fecha.isBefore(LocalDateTime.parse(txtFechaInicio.getText()))|| csd.fecha.isAfter(LocalDateTime.parse(txtFechaInicio.getText()))) craterFechaInicioFin.add(csd);
                    
                    }
                    craterFechaInicioFin.sort(cmp);
                    for(CraterSensadoData csdo:craterFechaInicioFin){
                        
                    }
                    
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        
    }

    
        
        
    
    
}
