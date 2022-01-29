/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static java.lang.Thread.sleep;
import javafx.scene.control.Alert;
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
    private int bateria;

    public Desplazarce(double xFinal, double yFinal, double hipotenusa, Rectangle rectangle, int bateria){
        
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.hipotenusa = hipotenusa;
        this.rectangle = rectangle;
        this.bateria= bateria;
    }
    
    @Override
    public void run() {
        try{
            if(xFinal == 0 && yFinal == 0){
                System.out.println("mismo Lugar");
            } else {
                double cantIntervalos = Math.round(hipotenusa/10);
                if (bateria >= cantIntervalos){
                double intervalosx = xFinal/cantIntervalos;
                double intervalosy = yFinal/cantIntervalos;
                int repeticiones = 0;
                while(repeticiones + 1 <= cantIntervalos){
                    rectangle.setLayoutX(rectangle.getLayoutX() + intervalosx);
                    rectangle.setLayoutY(rectangle.getLayoutY() + intervalosy);
                    repeticiones++;
                    bateria = bateria -1;
                    sleep(500);
                    
                }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("bateria");
                    alert.setTitle("Error");
                    alert.setContentText("bateria insuficiente para el movimiento");
                }
            }     
        }catch(Exception ex){
            System.out.println("el error fue "+ex.getStackTrace());
        }
        
    }
    
}
