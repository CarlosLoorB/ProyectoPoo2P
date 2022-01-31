/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosApp;



import Clases.Crater;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mluci
 */
public class CraterData {
     public static String ruta = "datos/crateres_info.txt";
    
    public static List<Crater> cargarCrater() {
        List<Crater> crateres = new ArrayList();
        try( BufferedReader bf =
                new BufferedReader(new FileReader(ruta))  ){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(",");
                Crater c = new Crater(parseInt(p[0]), p[1], parseDouble(p[2])
                        , parseDouble(p[3]), parseDouble(p[4]));
                crateres.add(c);
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return crateres;        
    }    
    
}
