/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import DatosApp.CraterData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author CAELOS JR 2018
 */
public abstract class MainRover implements InterfaceRover {
    
    private String nombre;
    protected Ubicacion ubicacion;
    private Rectangle rectangle;
    
    public MainRover(String nombre, Ubicacion ubicacion, Rectangle rectangle){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.rectangle = rectangle;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Ubicacion getUbicacion(){
        return ubicacion;
    }
    
    public Rectangle getRectangle(){
        return rectangle;
    }
    
    public String toString(){
        return nombre;
    }  
    
    @Override
    public void avanzar(int d) {
       double grados = rectangle.getRotate();
        double radianes = Math.toRadians(grados);
        
        double x = d*Math.cos(radianes);
        double y = d*Math.sin(radianes);
        
        rectangle.setLayoutX(rectangle.getLayoutX()+x);
        rectangle.setLayoutY(rectangle.getLayoutY()+y);
        
        ubicacion.setUbicacion(rectangle.getLayoutX()+x, rectangle.getLayoutY()+y);
    }

    @Override
    public void girar(int grados) {
        if(grados < 0){
            rectangle.setRotate(rectangle.getRotate()+grados+360);
        }else{
           rectangle.setRotate(rectangle.getRotate()+grados);
        }
    }

    @Override
    public void dirigirse(double x, double y) {
        double xFinal = 0;
        double yFinal = 0;
        double angulo = 0;
        yFinal = y - rectangle.getLayoutY();
        xFinal = x - rectangle.getLayoutX();
        double hipotenusa = Math.sqrt(Math.pow(xFinal, 2) + Math.pow(yFinal, 2));
        if (xFinal == 0 && yFinal > 0) {
            angulo = 90;
            rectangle.setRotate(angulo);
        } else if (xFinal == 0 && yFinal < 0) {
            angulo = -90;
            rectangle.setRotate(angulo);
        } else if (xFinal > 0 && yFinal == 0) {
            angulo = 0;
            rectangle.setRotate(angulo);
        } else if (xFinal < 0 && yFinal == 0) {
            angulo = 180;
            rectangle.setRotate(angulo);
        } else if(xFinal == 0 && yFinal == 0){
   
        }else {
            System.out.println(xFinal);
            System.out.println(yFinal);
            angulo = Math.atan(yFinal / xFinal);
            System.out.println(hipotenusa);
            System.out.println(angulo);
            angulo = Math.toDegrees(angulo);
            if (xFinal < 0) {
                angulo = angulo + 180;
            }
            System.out.println(angulo);
            rectangle.setRotate(angulo);
        }
        Thread t1 = new Thread(new Desplazarce(xFinal,yFinal,hipotenusa,rectangle));
        t1.setDaemon(true);
        t1.start();
//aqui se implementa el thread 
    }

    @Override
    public String sensar() {
        ArrayList minerales = new ArrayList();
        try (BufferedReader inputStream
                = new BufferedReader(new FileReader("datos/minerales.txt"))) {
            String linea = null;
            while ((linea = inputStream.readLine()) != null) {
                minerales.add(linea.split(","));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int contador = 0;

        List<Crater> crateres = CraterData.cargarCrater();
        
        for (Crater c: crateres) {
            contador++;
            Circle circulo = c.getCirculo();
            
            
            if (circulo.intersects(rectangle.boundsInLocalProperty().getValue())) {
                System.out.println(c.getNombre() + contador);
                System.out.println(circulo.boundsInLocalProperty().toString());
            } else{
                System.out.println("No funciona" + contador);
                
            }
            
        }

        return "hola";

    }

}
//rectangle.getLayoutX(), rectangle.getLayoutY(), 40, 40   @2c7ee72c
