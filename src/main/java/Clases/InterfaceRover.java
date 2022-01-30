/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;

/**
 *
 * @author CAELOS JR 2018
 */
public interface InterfaceRover {
    
    public void avanzar(int d);
    
    public void girar(int grados);
    
    public int dirigirse(double x, double y);
    
    public String sensar(List<Crater> crateres);
    
    public int cargar();
    
}
