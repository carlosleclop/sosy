/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;


/**
 *
 * @author CARLOS
 */
public class Escena extends BranchGroup {
    Texture texturaEscena;
    Material luz;
    ArrayList <Astro> astros;
    
    Alpha value; // Rotacion
    
    Escena(){
        astros = new ArrayList <> ();
        
        // Rotación de la estrella
        TransformGroup rotEstrella = createRotation();
        // El sol
        Estrella sol = new Estrella();
        rotEstrella.addChild(sol);
        
        this.addChild(rotEstrella);
        
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
    
    public void actPlay(){
//        for (int i = 0; i<this.numChildren(); i++ ){
//            ((Astro) this.getChild(i)).actPlay();
//        }
        for (Astro a : astros){
            a.actPlay();
        }
    }

    public void actPause(){
        for (Astro a : astros){
            a.actPause();
        }
    }
    
    
    private TransformGroup createRotation () {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 4000, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotator = new RotationInterpolator (value, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        return transform;
    }
    
}
