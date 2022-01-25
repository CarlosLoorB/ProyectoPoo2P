/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosApp;

import Clases.MainRover;
import Clases.RoverEolico;
import Clases.RoverSolar;
import Clases.Ubicacion;
import com.mycompany.mavenproject1.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author CAELOS JR 2018
 */
public class RoverData {
    public static String ruta = "datos/rovers-1.txt";
    
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
                    InputStream input = App.class.getResourceAsStream("rover.jpeg");
                    ImageView imgview = new ImageView(new Image(input, 40,40,false,false));
                    RoverSolar rover = new RoverSolar(p[0], u,imgview);
                    rovers.add(rover);
                } else{
                    InputStream input = App.class.getResourceAsStream("rover.jpeg");
                    ImageView imgview = new ImageView(new Image(input, 40,40,false,false));
                    RoverEolico rover = new RoverEolico(p[0], u,imgview);
                    rovers.add(rover);
                }
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rovers;        
    }    
    
}
