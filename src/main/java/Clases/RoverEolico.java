/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javafx.scene.image.ImageView;





/**
 *
 * @author USER
 */
public class RoverEolico extends MainRover{
    
    public RoverEolico(String nombre, Ubicacion ubicacion,ImageView imgview) {
        super(nombre, ubicacion, imgview);
    }

    @Override
    public void avanzar(int d) {
        
    }
    

    @Override
    public void girar(int grados) {
        if(grados < 0){
            imgview.setRotate(imgview.getRotate()+grados+360);
        }else{
           imgview.setRotate(imgview.getRotate()+grados);
        }
    }

    @Override
    public void dirigirse(double x, double y) {
         
    }

    @Override
    public String sensar() {
       return null;
    }

    @Override
    public void cargar() {
         
    }
    
}
