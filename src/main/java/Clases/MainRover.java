/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javafx.scene.image.ImageView;

/**
 *
 * @author CAELOS JR 2018
 */
public abstract class MainRover implements InterfaceRover {
    
    private String nombre;
    protected ImageView imgview;
    protected Ubicacion ubicacion;
    
    public MainRover(String nombre, Ubicacion ubicacion, ImageView imgview){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.imgview = imgview;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Ubicacion getUbicacion(){
        return ubicacion;
    }
    
    public ImageView getImgview(){
        return imgview;
    }
    
    public String toString(){
        return nombre;
    }  
    
    @Override
    public void avanzar(int d) {
       
    }

    @Override
    public void girar(int grados) {
        
    }

    @Override
    public void dirigirse(double x, double y) {
         
    }

    @Override
    public String sensar() {
       return null;
    }
}
