/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static java.lang.Thread.sleep;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author mluci
 */
public class Desplazarce implements Runnable {
    
    private double xFinal;
    private double yFinal;
    private double hipotenusa;
    private Rectangle rectangle;

    public Desplazarce(double xFinal, double yFinal, double hipotenusa, Rectangle rectangle){
        
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.hipotenusa = hipotenusa;
        this.rectangle = rectangle;
    }
    
    @Override
    public void run() {
        try{
            if(xFinal == 0 && yFinal == 0){
                System.out.println("mismo Lugar");
            } else {
                double cantIntervalos = Math.round(hipotenusa/10);
                double intervalosx = xFinal/cantIntervalos;
                double intervalosy = yFinal/cantIntervalos;
                int repeticiones = 0;
                while(repeticiones + 1 <= cantIntervalos){
                    rectangle.setLayoutX(rectangle.getLayoutX() + intervalosx);
                    rectangle.setLayoutY(rectangle.getLayoutY() + intervalosy);
                    repeticiones++;
                    sleep(500);
                }
            }     
        }catch(Exception ex){
            System.out.println("el pepe");
        }
    }
    
}
