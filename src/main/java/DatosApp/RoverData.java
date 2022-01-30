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
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

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
                    InputStream input = App.class.getResourceAsStream("rover.jpeg");//hacer en ell constructor
                    Image img = new Image(input, 40,40,false,false);
                    Rectangle rec = new Rectangle(40,40);
                    rec.setFill(new ImagePattern(img)); // hasta aqui 
                    int bateria = Integer.valueOf(p[5]);
                    double angulo =  Double.parseDouble(p[4]);
                    RoverSolar rover = new RoverSolar(p[0], u,rec,angulo,bateria);
                    rovers.add(rover);
                } else{
                    InputStream input = App.class.getResourceAsStream("rover.jpeg");
                    Image img = new Image(input, 40,40,false,false);
                    Rectangle rec = new Rectangle(40,40);
                   // rec.setStroke(Color.AQUA);
                    rec.setFill(new ImagePattern(img));
                    int bateria = Integer.valueOf(p[5]);
                    double angulo =  Double.parseDouble(p[4]);
                    RoverEolico rover = new RoverEolico(p[0], u,rec,angulo,bateria);
                    rovers.add(rover);
                }
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rovers;        
    }    
    
    public static void actualizarRovers(MainRover rover) {
        
        
        List<MainRover> rovers = cargarRovers();
        MainRover r1 = rovers.get(0);
        MainRover r2 = rovers.get(1);
        System.out.println(Math.round(r2.getRectangle().getLayoutX()));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            if (rover.getNombre().equals(r1.getNombre())){
                String nLinea = rover.getNombre() + "," + Math.round(rover.getRectangle().getLayoutX()) + ","
                         + Math.round(rover.getRectangle().getLayoutY()) + "," + "solar" + "," + Math.round(rover.getAngulo()) + "," + rover.getBateria();
                bw.write(nLinea);
                bw.newLine();
                String nLinea2 = r2.getNombre() + "," + Math.round(r2.getUbicacion().getUbicacionX()) + ","
                         + Math.round(r2.getUbicacion().getUbicacionY()) + "," + "eolico" + "," + r2.getAngulo() + "," + r2.getBateria();
                bw.write(nLinea2);
            } else {
                String nLinea = r1.getNombre() + "," + Math.round(r1.getUbicacion().getUbicacionX()) + ","
                         + Math.round(r1.getUbicacion().getUbicacionY()) + "," + "eolico" + "," + r1.getAngulo() + "," + r1.getBateria();
                bw.write(nLinea);
                bw.newLine();
                String nLinea2 = rover.getNombre() + "," + Math.round(rover.getRectangle().getLayoutX()) + ","
                        + Math.round(rover.getRectangle().getLayoutY()) + "," + "eolico" + "," + Math.round(rover.getAngulo()) + "," + rover.getBateria();
                bw.write(nLinea2);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
