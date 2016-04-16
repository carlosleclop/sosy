/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author CARLOS
 */
public class Nave extends TransformGroup{
    ArrayList <Point3d> puntosTrayectoria;
    ArrayList <Point3d> puntosOrientacion;
    Texture textura;
    TransformGroup tg;
    BranchGroup bg;
    
    Nave(){
        
        tg = new TransformGroup();
          Transform3D tf = new Transform3D();
           tf.setTranslation( new Vector3d(50,0,0) );
          Transform3D escalado = new Transform3D();
           escalado.setScale(2);
          tf.mul(escalado);
        tg.setTransform(tf);
        bg = new BranchGroup();
          
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
            modelo = archivo.load ("imgs/naveEspacial/naveEspacial.obj");
        } catch (FileNotFoundException e) {
            System.err.println (e);
            System.exit(1);
        } catch (ParsingErrorException e) {
            System.err.println (e);
            System.exit(1);
        } catch (IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        bg.addChild ( modelo.getSceneGroup() );
        tg.addChild(bg);
        this.addChild(tg);
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
