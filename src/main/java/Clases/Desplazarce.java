/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author mluci
 */
public class Desplazarce implements Runnable {
    
    private double xFinal;
    private double yFinal;
    private double hipotenusa;
    private MainRover rover;

    public Desplazarce(double xFinal, double yFinal, double hipotenusa, MainRover rover){
        
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.hipotenusa = hipotenusa;
        this.rover = rover;
    }
    
    @Override
    public void run() {
        
    }
    
}
