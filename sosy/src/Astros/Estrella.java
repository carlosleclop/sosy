/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import Model.Appearances;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;

/**
 *
 * @author CARLOS
 */
public class Estrella extends Astro {
    ArrayList <Planeta> planetas;
    Material luz;
    private BranchGroup bg;
    
    Estrella(){
        
    }
    Estrella(float radio){
        this.radio = radio;
        bg = new BranchGroup();
        bg.addChild(
                new Sphere (radio, 
                    Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 
                    64, Appearances.Texture.getAppearance() ));
        this.addChild(bg);
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
    
    public BranchGroup get(){
        return bg;
    }
}
