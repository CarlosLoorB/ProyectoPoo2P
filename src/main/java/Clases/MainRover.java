/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import DatosApp.CraterData;
import DatosApp.RoverData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
    Rectangle rectangle;
    private int bateria;
    private double angulo;
    
    public MainRover(String nombre, Ubicacion ubicacion, Rectangle rectangle,double angulo,int bateria){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.rectangle = rectangle;
        rectangle.setRotate(angulo);
        this.bateria = bateria;
        this.angulo = angulo;
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
    
    public int getBateria(){
        return bateria;
    }
    
    public double getAngulo(){
        return angulo;
    }
    
    public void setBateria(int bateria){
        this.bateria = bateria;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    public boolean equals(Object o){
            boolean verdad = false;
        if (o instanceof MainRover){
            MainRover comp = (MainRover)o;
            if(nombre.equals(comp.nombre))
                verdad = true;
        }
        else{
            verdad =  false;
        }
        return verdad;
    }
    
    @Override
    public void avanzar(int d) {   // actualizar posicion en el txt
        if (bateria > 1){
       double grados = rectangle.getRotate();
        double radianes = Math.toRadians(grados);
        
        double x = d*Math.cos(radianes);
        double y = d*Math.sin(radianes);
        
        rectangle.setLayoutX(rectangle.getLayoutX()+x);
        rectangle.setLayoutY(rectangle.getLayoutY()+y);
        
        ubicacion.setUbicacion(rectangle.getLayoutX()+x, rectangle.getLayoutY()+y);
        bateria = bateria -1;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("bateria");
                    alert.setTitle("Error");
                    alert.setContentText("bateria insuficiente para el movimiento");
        }
    }

    @Override
    public void girar(int grados) {
        if(grados < 0){
            rectangle.setRotate(rectangle.getRotate()+grados+360);
            angulo = rectangle.getRotate();
        }else{
           rectangle.setRotate(rectangle.getRotate()+grados);
           angulo = rectangle.getRotate();
        }
    }

    @Override
    public int dirigirse(double x, double y) {  //actualizar posicion en el txt
        double xFinal = 0;
        double yFinal = 0;
        double angulo = 0;
        yFinal = y - rectangle.getLayoutY();
        xFinal = x - rectangle.getLayoutX();
        double hipotenusa = Math.sqrt(Math.pow(xFinal, 2) + Math.pow(yFinal, 2));
        if (xFinal == 0 && yFinal > 0) {
            angulo = 90;
            rectangle.setRotate(angulo);
            this.angulo=angulo;
        } else if (xFinal == 0 && yFinal < 0) {
            angulo = -90;
            rectangle.setRotate(angulo);
            this.angulo=angulo;
        } else if (xFinal > 0 && yFinal == 0) {
            angulo = 0;
            rectangle.setRotate(angulo);
            this.angulo=angulo;
        } else if (xFinal < 0 && yFinal == 0) {
            angulo = 180;
            rectangle.setRotate(angulo);
            this.angulo=angulo;
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
            this.angulo = angulo;
        }
        long l = (Math.round(hipotenusa / 10));
        int cantIntervalos = (int) l;
        Desplazarce d = new Desplazarce(xFinal, yFinal, hipotenusa, rectangle, bateria);
        Thread t1 = new Thread(d);
        t1.setDaemon(true);
        t1.start();  //COMIENZA EL HILO
        //COMO ESTA EN SINCRONIZADO *-*
        return cantIntervalos;
        //roverSelec.setBateria(d.getBateria());
        //Thread t2 = new Threas(new ActualizarDatos(roverSelec))
        //t2.setDaemon(true);
        //t2.start();


        
        
//aqui se implementa el thread 
    }

    @Override
    public String sensar(List<Crater> crateres) {
        List<String> minerales = new ArrayList<>();
        String mineral = null;
        try (BufferedReader inputStream
                = new BufferedReader(new FileReader("datos/minerales.txt"))) {
            String linea = null;
            while ((linea = inputStream.readLine()) != null) {
                String [] strings = linea.split(",");
            minerales = new ArrayList<String>(Arrays.asList(strings));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int contador = 0;

        for (Crater c : crateres) {
            contador++;
            Circle circulo = c.getCirculo();

            if (circulo.getBoundsInParent().intersects(rectangle.getBoundsInParent())) {
                circulo.setFill(Color.RED);
                int max = minerales.size();
                int min = 0;
                int CantMinerales = (int) Math.floor(Math.random() * 5 + 1);
                System.out.println("cuanto minerales " + CantMinerales);
                LocalDateTime fecha = LocalDateTime.now();
                String linea = c.getId() + "," + c.getNombre() + "," + String.valueOf(fecha);
                ArrayList<Integer> mineralesHallados = new ArrayList<>();
                for (int x = 0; x < CantMinerales; x++) {
                    int numeroMineral = (int) Math.floor(Math.random() * 5 + 1);
                    System.out.println("numero random " + numeroMineral);
                    while (mineralesHallados.contains(numeroMineral)) {
                        numeroMineral = (int) Math.floor(Math.random() * 5 + 1);
                        System.out.println("numero random esyaba repetido " + numeroMineral);

                    }
                    mineralesHallados.add(numeroMineral);
                    linea += ";" + (minerales.get(numeroMineral)).toString();
                            if ( mineral != null){
                                mineral = mineral + (minerales.get(numeroMineral)).toString()+"  ";
                            }else{
                                mineral = (minerales.get(numeroMineral)).toString()+"  ";
                            }
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos/crateressense.txt" , true))) { //asegurarse de que se sobrescriba
                    writer.write(linea);
                    writer.newLine();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                System.out.println("No funciona" + contador);

            }

        }

        return mineral;

    }

}

