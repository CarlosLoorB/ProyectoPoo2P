/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import DatosApp.CraterSensadoData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseEvent;

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
    private TableView<TableData> tbvTabla;
    @FXML
    private TableColumn<TableData, String> columnaFecha;
    @FXML
    private TableColumn<TableData, String> columnaNombre;
    @FXML
    private TableColumn<TableData, String> columnaMinerales;
  
    ObservableList<TableData> datosTabla = FXCollections.observableArrayList();
    List<CraterSensadoData> datosCrateres;
      
    /**
    * Initializes the controller class.
    */  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Comparator<CraterSensadoData> cmp = ((CraterSensadoData csd1, CraterSensadoData csd2)->{
            int n = csd1.getNombre().compareTo(csd2.getNombre());
            if(n == 0){
                if(csd1.getFecha().isBefore(csd2.getFecha())) return -1;
                if(csd1.getFecha().isAfter(csd2.getFecha())) return 1;
                return 0;
            }
            return n;
            
        });
        
        try {
            datosCrateres = CraterSensadoData.leerCratersSensados();
            
        } catch (IOException ex) {}
        
        columnaFecha.setCellValueFactory(new PropertyValueFactory<TableData, String>("fecha"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<TableData, String>("nombre"));
        columnaMinerales.setCellValueFactory(new PropertyValueFactory<TableData, String>("minerales"));
       
        tablaOriginal();
        
    }    
    
    private void tablaOriginal() {
        
        datosCrateres.forEach(d -> datosTabla.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString())));
        tbvTabla.setItems(datosTabla);
        
    }

    @FXML
    private void filtrarExploracion(KeyEvent event) {
        datosTabla.clear();
        tbvTabla.getItems().clear();
        
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        
        try{
            fechaInicio = LocalDate.parse(txtFechaInicio.getText().trim());
            fechaFin = LocalDate.parse(txtFechaFin.getText().trim());
            
        }catch(Exception e) {
            tablaOriginal();
        }
        
        String material = txtMaterial.getText().trim();
        if(fechaInicio !=null && fechaFin != null && material != null){
            
            for(CraterSensadoData d : datosCrateres){
                if(d.getFecha().isBefore(fechaInicio) && 
                        d.getFecha().isAfter(fechaFin) &&
                            d.getMinerales().contains(material))
                                datosTabla.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
            }
        }
    }

    @FXML
    private void Regresar(MouseEvent event) throws IOException {
        App.setRoot("VistaIncial");
    }
    
    public static class TableData {
        
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty fecha;
        private final SimpleStringProperty minerales;
        
        private TableData(String nombre, String fecha, String minerales) {
            this.fecha = new SimpleStringProperty(fecha);
            this.nombre = new SimpleStringProperty(nombre);
            this.minerales = new SimpleStringProperty(minerales);
        }

        public String getNombre() {
            return nombre.get();
        }

        public String getFecha() {
            return fecha.get();
        }

        public String getMinerales() {
            return minerales.get();
        }
        
        
        
    }
    
}
