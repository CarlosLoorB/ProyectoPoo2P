/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static java.lang.Thread.sleep;
import javafx.scene.image.ImageView;

/**
 *
 * @author mluci
 */
public class Desplazarce implements Runnable {
    
    private double xFinal;
    private double yFinal;
    private double hipotenusa;
    private ImageView rover;

    public Desplazarce(double xFinal, double yFinal, double hipotenusa, ImageView rover){
        
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.hipotenusa = hipotenusa;
        this.rover = rover;
    }
    
    @Override
    public void run() {
        try{
        double cantIntervalos = Math.round(hipotenusa/10);
        double intervalosx = xFinal/cantIntervalos;
        double intervalosy = yFinal/cantIntervalos;
        int repeticiones = 0;
        while(repeticiones <= cantIntervalos){
            rover.setLayoutX(rover.getLayoutX()+intervalosx);
            rover.setLayoutY(rover.getLayoutY()+intervalosy);
            repeticiones++;
            sleep(1000);
        }
        }catch(Exception ex){
            System.out.println("el pepe");
        }
    }
    
}
