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
/*
    @Override
    public void avanzar(int d) {
        double grados = imgview.getRotate();
        double radianes = Math.toRadians(grados);
        
        double x = d*Math.cos(radianes);
        double y = d*Math.sin(radianes);
        
        imgview.setLayoutX(imgview.getLayoutX()+x);
        imgview.setLayoutY(imgview.getLayoutY()+y);
        
        ubicacion.setUbicacion(imgview.getLayoutX()+x, imgview.getLayoutY()+y);
    }
    
*/
    /*
    @Override
    public void girar(int grados) {
        if(grados < 0){
            imgview.setRotate(imgview.getRotate()+grados+360);
        }else{
           imgview.setRotate(imgview.getRotate()+grados);
        }
    }
    */
/*
    @Override
    public void dirigirse(double x, double y) {
         
    }

    @Override
    public String sensar() {
       return null;
    }
*/
    @Override
    public void cargar() {
         
    }
    
}
