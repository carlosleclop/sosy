/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Texture;

/**
 *
 * @author CARLOS
 */
abstract public class Astro extends BranchGroup {
    float radio;
    Texture tx;
    
    public float getRadio(){
        return radio;
    }
    public void setRadio(float r){
        radio = r;
    }
    public Texture getTexture(){
        return tx;
    }
    public void setTexture(Texture t){
        tx = t;
    }
    
    public void actPause(){
    
    }
    
    public void actPlay(){
        
    }
    
    
    
}
