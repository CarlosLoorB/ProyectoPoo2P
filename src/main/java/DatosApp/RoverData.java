/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosApp;

import Clases.Ubicacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAELOS JR 2018
 */
public class RoverData {
    public static String ruta = "datos/tiendas.txt";
    
    public static List<Rover> cargarRovers(Zona zona) {
        //cargarmos al informacion de los agentes
        List<Agente> agentes = AgenteData.cargarAgentes();
        System.out.println(agentes);
        
        List<Tienda> tiendas = new ArrayList<>();
        try( BufferedReader bf = 
                new BufferedReader(new FileReader(ruta)) ){
            String linea;
            while((linea = bf.readLine())!=null){
                //codigozona,nombretienda,ubx:uby,idAgente
                //001,Luisito,400:100,090002
                System.out.println(linea);
                String[] p = linea.split(",");
                if(p[0].equals(zona.getCodigo())){
                    String[] u = p[2].split(":");
                    Ubicacion ubicacion = new Ubicacion(Double.valueOf(u[0]),Double.valueOf(u[1]));

                    //obtenemos el agente por el id
                    Agente agente = 
                            agentes.stream()
                                    .filter( a -> a.getCedula().equals(p[3]))
                                    .findFirst()
                                    .orElse(null);

                    Tienda t = new Tienda(p[1],ubicacion,agente);
                    tiendas.add(t);
                }
            }         
        }  catch (IOException ex) {
            System.out.println("no se pudo cargar la informacion de las tiendas");
            ex.printStackTrace();
        }
        return tiendas;
    }    
    
}
