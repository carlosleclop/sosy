/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import java.util.ArrayList;
import javax.media.j3d.Material;

/**
 *
 * @author CARLOS
 */
public class Estrella extends Astro {
    ArrayList <Planeta> planetas;
    Material luz;
    
    Estrella(){
        
    }
    public void addPlaneta(Planeta p){
        planetas.add(p);
    }
    public void deletePlaneta(Planeta p){
        planetas.remove(p);
    }
    public ArrayList <Planeta> getPlanetas(){
        return planetas;
    }
    public Material getLuz(){
        return luz;
    }
    public void setLuz(Material m){
        luz = m;
    }
}
