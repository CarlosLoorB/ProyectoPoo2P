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
        double xFinal = 0;
        double yFinal = 0;
        //double grados = imgview.getRotate();
        //double radianes = Math.toRadians(grados);
        yFinal = y - imgview.getLayoutY();
         xFinal = x - imgview.getLayoutX();
        /*if (imgview.getLayoutX() < x) {
            xFinal = x - imgview.getLayoutX();
        } else if (x < imgview.getLayoutX()) {
            xFinal =x - imgview.getLayoutX() ;
        }
        if (imgview.getLayoutY() < y) {
            yFinal = y - imgview.getLayoutY();
        } else if (y < imgview.getLayoutY()) {
            yFinal = imgview.getLayoutY() - y;
        }*/
        double hipotenusa = Math.sqrt(Math.pow(xFinal, 2) + Math.pow(yFinal, 2));
        System.out.println(xFinal);
        System.out.println(yFinal);
        double angulo = Math.atan(xFinal / yFinal);
        System.out.println(hipotenusa);
        System.out.println(angulo);
        angulo = Math.toDegrees(angulo);
        if(xFinal<0){
            angulo = angulo +180;
        }
        System.out.println(angulo);
        imgview.setRotate(angulo);
        
        imgview.setLayoutX(x);
        imgview.setLayoutY(y);
        
        ubicacion.setUbicacion(imgview.getLayoutX()+x, imgview.getLayoutY()+y);
    }

    @Override
    public String sensar() {
       return null;
    }
}
