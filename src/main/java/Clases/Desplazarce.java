/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import DatosApp.RoverData;
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
    private MainRover Rover;

    public Desplazarce(double xFinal, double yFinal, double hipotenusa, MainRover Rover){
        
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.hipotenusa = hipotenusa;
        this.Rover=Rover;
    }
    
    public int getBateria(){
        return Rover.getBateria();
    }
    
    @Override
    public void run() {
        try{
            if(xFinal == 0 && yFinal == 0){
                System.out.println("mismo Lugar");
            } else {
                double cantIntervalos = Math.round(hipotenusa/10);
                if (Rover.getBateria() >= cantIntervalos){
                double intervalosx = xFinal/cantIntervalos;
                double intervalosy = yFinal/cantIntervalos;
                int repeticiones = 0;
                while(repeticiones + 1 <= cantIntervalos){
                    Rover.getRectangle().setLayoutX(Rover.getRectangle().getLayoutX() + intervalosx);
                    Rover.getRectangle().setLayoutY(Rover.getRectangle().getLayoutY() + intervalosy);
                    repeticiones++;
                    int nuevaBat= Rover.getBateria() -1;
                    Rover.setBateria(nuevaBat) ;
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
