/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosApp;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author USER
 */
public class CraterSensadoData {
    
    public String nombre;
    public LocalDate fecha;
    public List<String> minerales;
    
    public CraterSensadoData(String nombre, LocalDate fecha, List<String> minerales){
        this.nombre = nombre;
        this.fecha = fecha;
        this.minerales = minerales;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public LocalDate getFecha(){
        return fecha;
    }
    
    public List<String> getMinerales(){
        return minerales;
    }
    
    public static List<CraterSensadoData> leerCratersSensados()throws IOException{
        List<CraterSensadoData> cratersSensados = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader("datos/crateressense.txt"))){
            String linea;
            while((linea = reader.readLine())!=null){
                String[] lista1 = linea.split(",");
                String[] lista2 = lista1[2].split(";");
                List<String> minerales = new ArrayList<>(Arrays.asList(lista2[1]));
                cratersSensados.add(new CraterSensadoData(lista1[1],LocalDate.parse(lista2[0]),minerales));
            }
        }
        return cratersSensados;
    }
}
