/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


/**
 *
 * @author USER
 */
public class Ubicacion {
    
    private double ubicacionX;
    private double ubicacionY;
    /**
     * Constructor de la clase Ubicacion
     * @param ubicacionX double
     * @param ubicacionY double
     */
    public Ubicacion(double ubicacionX, double ubicacionY){
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
    }
    /**
     * Retorna la ubicacion en X
     * @return double
     */
    public double getUbicacionX(){
        return ubicacionX;
    }
    /**
     * Retorna la ubicacion en Y
     * @return double
     */
    public double getUbicacionY(){
        return ubicacionY;
    }
    /**
     * Permite establecer una nueva posicion en X y Y
     * @param x double
     * @param y double
     */
    public void setUbicacion(double x, double y){
        this.ubicacionX = x;
        this.ubicacionY = y;
    }
}
