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
    
    Alpha value; // Rotacion
    
    Escena(){
        astros = new ArrayList <> ();
        todo = new BranchGroup();


        // Rotación de la estrella
        TransformGroup rotEstrella = createRotation(100000);
        Estrella sol = new Estrella(10.0f);
        rotEstrella.addChild(sol);
        todo.addChild(rotEstrella);
                
/*        TransformGroup desplazamientoTierra = createTranslate();
        Planeta tierra = new Planeta(5.0f);
        desplazamientoTierra.addChild(tierra);
        todo.addChild(desplazamientoTierra);*/

        TransformGroup rotacionTierra = createRotation(10000);
        TransformGroup desplazamientoTierra = createTranslate(40);
        TransformGroup rotacionTierra2 = createRotation(3000);
        Planeta tierra = new Planeta(5.0f);
        rotacionTierra2.addChild(tierra);
        desplazamientoTierra.addChild(rotacionTierra2);
        rotacionTierra.addChild(desplazamientoTierra);
        todo.addChild(rotacionTierra);


        //todo.addChild(tierra);
        crearLuces(this);
        this.addChild(todo);
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

        /*
        // PRIMERA LUZ
         Color3f white;
         Vector3f direction;
         white = new Color3f (1.0f, 1.0f, 1.0f);
         direction = new Vector3f (-4.0f, -2.0f, -3.0f);
         aLight = new DirectionalLight (white, direction);
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
         aLight.setCapability(Light.ALLOW_STATE_WRITE);
         aLight.setEnable (true);
         bg.addChild(aLight);

        // SEGUNDA LUZ
         aLight = new DirectionalLight (new Color3f (0.7f, 0.7f, 0.7f), new Vector3f (3.0f, 2.0f, 0.0f));
         aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
         aLight.setCapability(Light.ALLOW_STATE_WRITE);
         aLight.setEnable (true);
         bg.addChild(aLight);*/
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
        return createRotation (4000);
    }
    private TransformGroup createRotation (int velocidad) {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidad, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotator = new RotationInterpolator (value, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 10000.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        return transform;
    }
    private TransformGroup createTranslate(double distancia){
        TransformGroup transform = new TransformGroup ();
        
        Transform3D tr = new Transform3D ();
        tr.setTranslation( new Vector3d(distancia,0,0) );
        
        transform.setTransform(tr);
        
        return transform;
    }
    
}
