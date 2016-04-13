/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import java.util.ArrayList;

/**
 *
 * @author CARLOS
 */
public class Planeta extends AstroOpaco {
    
    ArrayList <Satelite> satelites;
    ArrayList <Anillo> anillos;
    
    Planeta(){
        
    }
    public void addSatelite(Satelite s){
        satelites.add(s);
    }
    public boolean deleteSatelite(Satelite s){
        return satelites.remove(s);
    }
    public void addAnillo(Anillo a){
        anillos.add(a);
    }
    public boolean deleteAnillo(Anillo a){
        return anillos.remove(a);
    }
    public ArrayList <Satelite> getSatelites(){
        return satelites;
    }
    public ArrayList <Anillo> getAnillos(){
        return anillos;
    }
    
}
