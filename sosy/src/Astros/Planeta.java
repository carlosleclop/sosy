/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

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
public class Planeta extends AstroOpaco {
    
    ArrayList <Satelite> satelites;
    ArrayList <Anillo> anillos;
    private BranchGroup bg;
    
    Planeta(){
        
    }
    Planeta(float radio){
        this.radio = radio;
        bg = new BranchGroup();
        bg.addChild(
                new Sphere (radio, 
                    Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 
                    64, getAppearance("imgs/tierra.jpg") ));
        this.addChild(bg);
    }
    Planeta(float radio, String textureString){
        this.radio = radio;
        bg = new BranchGroup();
        bg.addChild(
                new Sphere (radio, 
                    Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 
                    64, getAppearance(textureString) ));
        this.addChild(bg);
    }

    
    private Appearance getAppearance(String textureString){
        Appearance ap = new Appearance();
        Texture aTexture = new TextureLoader (textureString, null).getTexture();
        ap.setTexture (aTexture);

/*        ap.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            5.0f ));                            // Brillo
        */
        ap.setMaterial (new Material (
            new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.40f, 0.40f, 0.40f),   // Color especular
            6.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
        return ap;
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
