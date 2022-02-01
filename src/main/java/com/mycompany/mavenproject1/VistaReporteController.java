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
         */
        try {
            datosCrateres = CraterSensadoData.leerCratersSensados();

        } catch (IOException ex) {
        }

        columnaFecha.setCellValueFactory(new PropertyValueFactory<TableData, String>("fecha"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<TableData, String>("nombre"));
        columnaMinerales.setCellValueFactory(new PropertyValueFactory<TableData, String>("minerales"));

        tbvTabla.getItems().clear();
        tablaOriginal();
    }

    private void tablaOriginal() {

        datosCrateres.forEach(d -> datosTabla.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString())));
        tbvTabla.setItems(datosTabla);

    }

    private ObservableList<TableData> filtrar() {
        ObservableList<TableData> datos = FXCollections.observableArrayList();
        datosTabla.clear();
        tbvTabla.getItems().clear();

        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;

        try {
            fechaInicio = LocalDate.parse(txtFechaInicio.getText().toLowerCase().trim());
            System.out.println("Esta es la fecha de inicio: " + fechaInicio);
        } catch (Exception e) {
            tablaOriginal();
        }

        try {
            fechaFin = LocalDate.parse(txtFechaFin.getText().toLowerCase().trim());
            System.out.println("Esta es la fecha fin: " + fechaFin);
        } catch (Exception ex) {
            tablaOriginal();
        }

        String material = txtMaterial.getText().toLowerCase().trim();

        if (fechaInicio != null && fechaFin != null && material != null) {

            for (CraterSensadoData d : datosCrateres) {
                if (d.getFecha().isAfter(fechaInicio) || d.getFecha().equals(fechaInicio)
                        && d.getFecha().isBefore(fechaFin) || d.getFecha().equals(fechaFin)
                        && d.getMinerales().contains(material)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        } else if (fechaInicio == null && fechaFin != null && material != null) {
            for (CraterSensadoData d : datosCrateres) {
                if (d.getFecha().isBefore(fechaFin) || d.getFecha().equals(fechaFin)
                        && d.getMinerales().contains(material)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        } else if (fechaInicio != null && fechaFin == null && material != null) {
            for (CraterSensadoData d : datosCrateres) {
                if (d.getFecha().isAfter(fechaInicio) || d.getFecha().equals(fechaInicio)
                        && d.getMinerales().contains(material)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        } else if (fechaInicio != null && fechaFin != null && material == null) {
            for (CraterSensadoData d : datosCrateres) {
                if (d.getFecha().isAfter(fechaInicio) || d.getFecha().equals(fechaInicio)
                        && d.getFecha().isBefore(fechaFin) || d.getFecha().equals(fechaFin)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        } else if (fechaInicio != null && fechaFin == null && material == null) {
            for (CraterSensadoData d : datosCrateres) {
                if (d.getFecha().isAfter(fechaInicio) || d.getFecha().equals(fechaInicio)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        } else if (fechaInicio == null && fechaFin != null && material == null) {
            for (CraterSensadoData d : datosCrateres) {
                if (d.getFecha().isBefore(fechaFin) || d.getFecha().equals(fechaFin)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        } else if (fechaInicio == null && fechaFin == null && material != null) {
            for (CraterSensadoData d : datosCrateres) {
                if (d.getMinerales().contains(material)) {
                    datos.add(new TableData(d.getNombre(), d.getFecha().toString(), d.getMinerales().toString()));
                }
            }
        }
        System.out.println("Esta es la fecha de inicio: " + fechaInicio);
        System.out.println("Esta es la fecha fin: " + fechaFin);

        return datos;
    }

    @FXML
    private void buscarExploracion(ActionEvent event) {
        tbvTabla.getItems().clear();
        tbvTabla.setItems(filtrar());

    }
/*
    @FXML
    private void refrescarTabla(ActionEvent event) {
        tbvTabla.getItems().clear();
        tbvTabla.setItems(datosTabla);
    }
*/
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
