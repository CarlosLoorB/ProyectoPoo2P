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
    
    public Ubicacion(double ubicacionX, double ubicacionY){
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
    }
    
    public double getUbicacionX(){
        return ubicacionX;
    }
    
    public double getUbicacionY(){
        return ubicacionY;
    }
}
