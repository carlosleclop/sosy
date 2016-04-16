/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


/**
 *
 * @author CARLOS
 */
public class Escena extends BranchGroup {    
    
    Texture texturaEscena;
    Material luz;
    ArrayList <Astro> astros;
    
    BranchGroup todo;
        
    Escena(){
        astros = new ArrayList <> ();
        todo = new BranchGroup();
        
        Estrella sol = new Estrella(10.0f, "imgs/sol.jpg", 100000);
          Planeta tierra = new Planeta(5.0f, "imgs/tierra.jpg", 3000, 40, 10000);
            Satelite luna = new Satelite(2.0f, "imgs/moon.jpg", 0, 10, 1000);
          tierra.addSatelite(luna);
        sol.addPlaneta(tierra);
        
        todo.addChild(sol);
        
        crearLuces(this);
        this.addChild(todo);
        
        astros.add(sol);
        astros.add(tierra);
        astros.add(luna);
    }
    
    private void crearLuces(BranchGroup bg){

        // LUZ AMBIENTE
         Light aLight;
         //aLight = new AmbientLight (new Color3f (0.2f, 0.2f, 0.2f));
         aLight = new AmbientLight (new Color3f (0.5f, 0.5f, 0.5f));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setEnable(true);
         bg.addChild(aLight);
         
        // LUZ DEL SOL
         Color3f white = new Color3f (1.0f, 1.0f, 1.0f);
        // Vector3f direction = new Vector3f (-4.0f, -2.0f, -3.0f);
         aLight = new PointLight (true, new Color3f (1.0f, 0.9f, 0.9f), new Point3f(0,0,0), new Point3f(1,0,0));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
         aLight.setCapability(Light.ALLOW_STATE_WRITE);
         aLight.setEnable (true);
         bg.addChild(aLight);
    }
    public Material getLuzEscena(){
        return luz;
    }
    public void setLuzEscena(Material l){
        luz = l;
    }
    public Texture getTexturaEscena(){
        return texturaEscena;
    }
    public void setTexturaEscena(Texture t){
        texturaEscena = t;
    }
    public ArrayList <Astro> getAstros(){
        return astros;
    }
    public void addAstro(Astro a){
        astros.add(a);
        this.addChild(a);
    }
    public void deleteAstro(Astro a){
        astros.remove(a);
        this.removeChild(a);
    }
    
    void setRotationOnOff (boolean onOff) {
        if (onOff)
            actPlay();
        else
            actPause();
    }
    
    public void actPlay(){
        for (Astro a : astros){
            a.actPlay();
        }
    }

    public void actPause(){
        for (Astro a : astros){
            a.actPause();
        }
    }
    
}
