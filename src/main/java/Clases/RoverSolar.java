 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author USER
 */
public class RoverSolar extends MainRover{
    /**
     * Constructor de la clase RoverSolar
     * @param nombre String
     * @param ubicacion Ubicacion
     * @param rectangle Rectangle
     * @param angulo double
     * @param bateria int
     */
    public RoverSolar(String nombre, Ubicacion ubicacion,Rectangle rectangle,double angulo,int bateria) {
        super(nombre, ubicacion, rectangle ,angulo,bateria );
    }
    /**
     * Permite al objeto cargar su bateria
     * @return int
     */
    @Override
    public int cargar() {
        int cantIntervalos = super.dirigirse(100, 100);
        if (getBateria() > cantIntervalos) {
                        super.setBateria(100);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("bateria");
                        alert.setTitle("Error");
                        alert.setContentText("bateria insuficiente para el movimiento, el robot ha quedado inutilizable");
                        alert.showAndWait();
                    }
        return cantIntervalos;
    }

}
