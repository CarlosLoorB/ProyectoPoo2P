/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Clases.Crater;
import Clases.MainRover;
import Clases.Ubicacion;
import DatosApp.CraterData;
import DatosApp.RoverData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private Label DescripcionCrater;
    @FXML
    private StackPane st1;
    @FXML
    private TextArea DispComando;
    @FXML
    private TextField ventanaComando;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<MainRover> rovers = RoverData.cargarRovers();
       pestanaRobot.getItems().addAll(rovers);
       List<Crater> crateres = CraterData.cargarCrater();
       for(Crater crater: crateres){
           Circle c = new Circle(crater.getRadio(),new Color(0,0,0,0));
           c.setStroke(Color.RED);
           StackPane st = new StackPane();
           st.getChildren().addAll(c);
           st.setLayoutX(crater.getLatitud());
           st.setLayoutY(crater.getLongitud());
           paneMapa.getChildren().addAll(st);
           st.setOnMouseClicked((MouseEvent ev) -> {
               ev.consume();
               try (BufferedReader Cs
                       = new BufferedReader(new FileReader("datos/crateressense.txt")); BufferedReader Cl
                       = new BufferedReader(new FileReader("datos/crateres_info.txt"));) {
                   String linea;
                   boolean encontrado = false;
                   while ((linea = Cs.readLine()) != null) {
                       String[] p = linea.split(",");
                       if(Integer.valueOf(p[0]) == crater.getId()){
                         DescripcionCrater.setText(linea);
                         encontrado = true;
                       }
                   }
                   while ((linea = Cl.readLine()) != null && encontrado == false) {
                       String[] p = linea.split(",");
                       if(Integer.valueOf(p[0]) == crater.getId()){
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

    @FXML
    private void seleccionarRobot(ActionEvent event) {
        MainRover roverSelec = pestanaRobot.getValue();
        System.out.println(roverSelec.getNombre());
        ImageView imgview = null;
                HBox hbox = new HBox();
                try {
                    InputStream input = App.class.getResource("rover.jpeg").openStream();
                    Image img = new Image(input, 20, 20,false,false);
                    imgview = new ImageView(img);
                } catch (NullPointerException | IOException ex) {
                    //no hay la imagen buscada
                    imgview = new ImageView();
        }
        st1.setAlignment(Pos.TOP_LEFT);
        st1.getChildren().clear();
        st1.getChildren().addAll(imgview);
        st1.setLayoutX(roverSelec.getUbicacion().getUbicacionX());
        st1.setLayoutY(roverSelec.getUbicacion().getUbicacionY());

    }

    @FXML
    private void ejecutarComando(KeyEvent event) {
        // tenemos el rover al que vamos a mover 
        MainRover roverSelec = pestanaRobot.getValue();
        int pos = Integer.valueOf(ventanaComando.getText());
        roverSelec.avanzar(pos);
        Ubicacion ubiActual = roverSelec.getUbicacion();
        
    }

    
}
