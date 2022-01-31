/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 *
 * @author mluci
 */
public class Crater {
    private int id; 
    private String nombre;
    private double latitud;
    private double longitud;
    private double radio;
    private Circle circulo;
    
    /**
     * Constructor de la clase Crater
     * @param id recibe un int
     * @param nombre recibe un String
     * @param latitud recibe un double
     * @param longitud recibe un double 
     * @param radio recibe un double
     */
    public Crater(int id, String nombre, double latitud, double longitud, double radio){
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
        this.circulo = new Circle(radio,new Color(0,0,0,0));
    }
    /**
     * Metodo para retornar el id
     * @return int 
     */
    public int getId(){
        return id;
    }
    /**
     * Metodo para retornar el nombre
     * @return String
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Metodo para retornar la latitud
     * @return double
     */
    public double getLatitud(){
        return latitud;
    }
    /**
     * Metodo para retornar la longitud
     * @return double
     */
    public double getLongitud(){
        return longitud;
    }
    /**
     * Metodo para retornar el radio
     * @return double
     */
    public double getRadio(){
        return radio;
    }
    /**
     * Metodo para retornar el circulo
     * @return Circle
     */
    public Circle getCirculo(){
        return circulo;
    }
    /**
     * Metodo para transformar el objeto a un String
     * @return String
     */
    public String toString(){
        return "id:" + id + "nombre:" + nombre + "latitud:" + latitud +
                "longitud:" + longitud + "radio:" + radio;
    }
}
