/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

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
    
    public Crater(int id, String nombre, double latitud, double longitud, double radio){
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }
    
    public int getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public double getLatitud(){
        return latitud;
    }
    
    public double getLongitud(){
        return longitud;
    }
    
    public double getRadio(){
        return radio;
    }
    
    public String toString(){
        return "id:" + id + "nombre:" + nombre + "latitud:" + latitud +
                "longitud:" + longitud + "radio:" + radio;
    }
}
