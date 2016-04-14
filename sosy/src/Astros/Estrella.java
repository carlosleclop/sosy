/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import Model.Appearances;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

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
                    64, getAppearance() ));
        this.addChild(bg);
    }
    public Appearance getAppearance(){
        Appearance ap = new Appearance();
        Texture aTexture = new TextureLoader ("imgs/sol.jpg", null).getTexture();
        ap.setTexture (aTexture);
        ap.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            17.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
        return ap;
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
