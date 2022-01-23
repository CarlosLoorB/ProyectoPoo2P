/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosApp;

import Clases.MainRover;
import Clases.RoverEolico;
import Clases.RoverSolar;
import Clases.Ubicacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAELOS JR 2018
 */
public class RoverData {
    public static String ruta = "datos/tiendas.txt";
    
    public static List<MainRover> cargarRovers() {
        //cargarmos al informacion de los agentes
        List<MainRover> rovers = new ArrayList();
        try( BufferedReader bf =
                new BufferedReader(new FileReader(ruta))  ){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(",");
                Ubicacion u = new Ubicacion(parseDouble(p[1]), parseDouble(p[2]));
                if(p[3].equals("solar")){
                    RoverSolar rover = new RoverSolar(p[0], u);
                } else{
                    RoverEolico rover = new RoverEolico(p[0], u);
                }
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rovers;
    }    
    
}
