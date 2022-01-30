/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


import DatosApp.RoverData;
import static DatosApp.RoverData.ruta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.List;

/**
 *
 * @author mluci
 */
public class ActualizarDatos implements Runnable {

    private MainRover rover;
    private int espera;

    public ActualizarDatos(MainRover rover, int espera) {
        this.rover = rover;
        this.espera = espera;
    }

    @Override
    public void run() {
        try {
            sleep(espera);
            List<MainRover> rovers = RoverData.cargarRovers();
            MainRover r1 = rovers.get(0);
            MainRover r2 = rovers.get(1);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
                if (rover.getNombre().equals(r1.getNombre())) {
                    String nLinea = rover.getNombre() + "," + Math.round(rover.getRectangle().getLayoutX()) + ","
                            + Math.round(rover.getRectangle().getLayoutY()) + "," + "solar" + "," + Math.round(rover.getAngulo()) + "," + rover.getBateria();
                    bw.write(nLinea);
                    bw.newLine();
                    String nLinea2 = r2.getNombre() + "," + Math.round(r2.getUbicacion().getUbicacionX()) + ","
                            + Math.round(r2.getUbicacion().getUbicacionY()) + "," + "eolico" + "," + r2.getAngulo() + "," + r2.getBateria();
                    bw.write(nLinea2);
                } else {
                    String nLinea = r1.getNombre() + "," + Math.round(r1.getUbicacion().getUbicacionX()) + ","
                            + Math.round(r1.getUbicacion().getUbicacionY()) + "," + "solar" + "," + r1.getAngulo() + "," + r1.getBateria();
                    bw.write(nLinea);
                    bw.newLine();
                    String nLinea2 = rover.getNombre() + "," + Math.round(rover.getRectangle().getLayoutX()) + ","
                            + Math.round(rover.getRectangle().getLayoutY()) + "," + "eolico" + "," + Math.round(rover.getAngulo()) + "," + rover.getBateria();
                    bw.write(nLinea2);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (InterruptedException ex2) {
            ex2.printStackTrace();
        }
    }
}
