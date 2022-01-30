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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<CraterSensadoData> tbvTabla;
    
    private ObservableList<CraterSensadoData> craters;
    @FXML
    private TableColumn<CraterSensadoData, LocalDateTime> columnaFecha;
    @FXML
    private TableColumn<CraterSensadoData, String> columnaNombre;
    @FXML
    private TableColumn<CraterSensadoData, List<String>> columnaMinerales;
    /**
     * Initializes the controller class.
     */
    private ObservableList<CraterSensadoData> cratersFecha;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            tbvTabla.setItems((ObservableList<CraterSensadoData>) CraterSensadoData.leerCratersSensados());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       /*
        Comparator<CraterSensadoData> cmp = ((CraterSensadoData csd1, CraterSensadoData csd2)->{
            int n = csd1.getNombre().compareTo(csd2.getNombre());
            if(n == 0){
                if(csd1.getFecha().isBefore(csd2.getFecha())) return -1;
                if(csd1.getFecha().isAfter(csd2.getFecha())) return 1;
                return 0;
            }
            return n;
            
        });
        
        cratersFecha.sort(cmp);
        columnaFecha.setCellValueFactory(new PropertyValueFactory<CraterSensadoData,LocalDateTime>("fecha"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<CraterSensadoData,String>("nombre"));
        columnaMinerales.setCellValueFactory(new PropertyValueFactory<CraterSensadoData,List<String>>("minerales"));
       
        craters = FXCollections.observableArrayList(cratersFecha);
        tbvTabla.setItems(craters);
        */
        
    }    

    @FXML
    private void filtrarFecha(KeyEvent event) {
        
        if(txtFechaInicio.getText()!=null && txtFechaFin.getText() != null){
            
            if(event.getCode() == KeyCode.ENTER){
                try{
                    for(CraterSensadoData csd : CraterSensadoData.leerCratersSensados()){
                        if(csd.fecha.isBefore(LocalDateTime.parse(txtFechaInicio.getText().trim()))&& csd.fecha.isAfter(LocalDateTime.parse(txtFechaInicio.getText().trim()))) cratersFecha.add(csd);
                          
                    }
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        
    }

    
        
        
    
    
}
