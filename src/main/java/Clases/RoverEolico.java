/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;






/**
 *
 * @author USER
 */
public class RoverEolico extends MainRover{
    
    public RoverEolico(String nombre, Ubicacion ubicacion,Rectangle rectangle,double angulo,int bateria) {
        super(nombre, ubicacion, rectangle, angulo, bateria);
    }

    @Override
    public int cargar() {
        super.rectangle.setRotate(270);
        super.setBateria(100);
        return 420;
         
    }
    
}
