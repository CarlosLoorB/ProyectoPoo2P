/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Clases.ActualizarDatos;
import Clases.Crater;
import Clases.Desplazarce;
import Clases.MainRover;
import Clases.RoverEolico;
import Clases.RoverSolar;
import Clases.Ubicacion;
import DatosApp.CraterData;
import DatosApp.RoverData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    private Label DescripcionCrater;
    @FXML
    private TextArea DispComando;
    @FXML
    private TextField ventanaComando;
    @FXML
    private Pane roverPane;

    private List<Crater> craters;
    
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hola");
       List<MainRover> rovers = RoverData.cargarRovers();
       pestanaRobot.getItems().addAll(rovers);
       craters = CraterData.cargarCrater();
       for(Crater crater: craters){
           Circle c = crater.getCirculo();
           c.setStroke(Color.RED);
           paneMapa.getChildren().addAll(c);
           c.setLayoutX(crater.getLatitud());
           c.setLayoutY(crater.getLongitud());
           c.setOnMouseClicked((MouseEvent ev) -> {
               ev.consume();
               try (BufferedReader Cs
                       = new BufferedReader(new FileReader("datos/crateressense.txt")); BufferedReader Cl
                       = new BufferedReader(new FileReader("datos/crateres_info.txt"));) {
                   String linea;
                   boolean encontrado = false;
                   while ((linea = Cs.readLine()) != null) {
                       String[] p = linea.split(",");
                       if(Integer.parseInt(p[0]) == crater.getId()){
                         DescripcionCrater.setText(linea);
                         encontrado = true;
                       }
                   }
                   while ((linea = Cl.readLine()) != null && encontrado == false) {
                       String[] p = linea.split(",");
                       if(Integer.parseInt(p[0]) == crater.getId()){
                         DescripcionCrater.setText(p[0]+","+p[1]);
                       }
                   }
               } catch (IOException ex) {
                   Alert a = new Alert(Alert.AlertType.ERROR);
                   a.setContentText("Error al leer el archivo" + ex.getMessage());
                   a.show();
               }
           });
       }    
    }    
    /**
     * Permite seleccionar el rover deseado y mostrarlo en la pantalla
     * @param event 
     */
    @FXML
    private void seleccionarRobot(ActionEvent event) {
        MainRover roverSelec = pestanaRobot.getValue();
        pestanaRobot.setOnMouseClicked((event2)->{     
            Platform.runLater(()->{
                paneMapa.getChildren().remove(roverSelec.getRectangle());
            });     
        });
        System.out.println(roverSelec.getNombre());      
        paneMapa.getChildren().addAll(roverSelec.getRectangle());
        roverSelec.getRectangle().setLayoutX(roverSelec.getUbicacion().getUbicacionX());
        roverSelec.getRectangle().setLayoutY(roverSelec.getUbicacion().getUbicacionY());
    }
    /**
     * Permite leer el comando ingresado y ejecutarlo
     * @param event 
     */
    @FXML
    private void ejecutarComando(KeyEvent event) {
        MainRover roverSelec = pestanaRobot.getValue();
        if (event.getCode() == KeyCode.ENTER) {
            String palabra = ventanaComando.getText().trim();
            DispComando.appendText(palabra + "\n");
            try {
                if (ventanaComando.getText().equals("avanzar")) {
                    roverSelec.avanzar(10);
                    ventanaComando.clear();
                    RoverData.actualizarRovers(roverSelec);
                } else if (ventanaComando.getText().trim().contains("girar:")) {
                    String[] lista = ventanaComando.getText().split(":");
                    if (lista.length == 2) {
                        roverSelec.girar(Integer.parseInt(lista[1]));
                        ventanaComando.clear();
                        RoverData.actualizarRovers(roverSelec);
                    } else {
                        ventanaComando.clear();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Comando invalido");
                        alert.setContentText("Ingrese un solo angulo.");
                        alert.showAndWait();
                    }
                } else if (ventanaComando.getText().trim().contains("hazlo:")) {
                    String[] lista = ventanaComando.getText().split(":");
                    int cantIntervalos = roverSelec.dirigirse(Double.parseDouble(lista[1]), Double.parseDouble(lista[2]));
                    int espera = ((cantIntervalos * 100) + 100);
                    roverSelec.setBateria(roverSelec.getBateria() - cantIntervalos);
                    RoverData.actualizarRoversT(roverSelec, espera);
                    ventanaComando.clear();
                } else if (ventanaComando.getText().equals("sensar")) {
                    String mineralHallado = roverSelec.sensar(craters);
                    DescripcionCrater.setText(mineralHallado);
                    ventanaComando.clear();
                    RoverData.actualizarRovers(roverSelec);
                } else if (ventanaComando.getText().equals("cargar")) {
                    if (roverSelec instanceof RoverEolico) {
                        RoverEolico rover = (RoverEolico) roverSelec;
                        int cantIntervalos = rover.cargar();
                        int espera = ((cantIntervalos * 100) + 100);
                        ventanaComando.clear();
                        RoverData.actualizarRoversT(roverSelec, espera);
                    } else if (roverSelec instanceof RoverSolar) {
                        RoverSolar rover = (RoverSolar) roverSelec;
                        rover.cargar();
                        RoverData.actualizarRovers(rover);
                    }
                } else {
                    ventanaComando.clear();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Comando invalido");
                    alert.setContentText("Ingrese un comando correcto.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException x) {
                ventanaComando.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Comando invalido");
                alert.setContentText("Ingrese numeros no letras.");
                alert.showAndWait();
            }

        }
    }
}
