/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import java.util.ArrayList;
import javax.media.j3d.Texture;
import javax.vecmath.Point3d;

/**
 *
 * @author CARLOS
 */
public class Nave {
    ArrayList <Point3d> puntosTrayectoria;
    ArrayList <Point3d> puntosOrientacion;
    Texture textura;
    
    Nave(){
        
    }
    public ArrayList <Point3d> getPuntosTrayectoria(){
        return puntosTrayectoria;
    }
    public void setPuntosTrayectoria(ArrayList <Point3d> pt){
        puntosTrayectoria = pt;
    }
    public ArrayList <Point3d> getPuntosOrientacion(){
        return puntosOrientacion;
    }
    public void setPuntosOrientacion(ArrayList <Point3d> po){
        puntosOrientacion = po;
    }
    public Texture getTextura(){
        return textura;
    }
    public void setTextura(Texture t){
        textura = t;
    }
    
    public void actGo(){
        
    }
    public void actStop(){
        
    }
    
}
