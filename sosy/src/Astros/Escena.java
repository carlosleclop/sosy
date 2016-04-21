/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import com.sun.j3d.utils.geometry.Sphere;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleStripArray;
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
    Nave nave;
    
    BranchGroup todo;
        
    Escena(){
        int factorVelocidad = 800000;
        int factorVelocidadS = 1000000;
        
        astros = new ArrayList <> ();
        todo = new BranchGroup();
        
        nave = new Nave();
        todo.addChild(nave);
        
        Estrella sol = new Estrella(4f, "imgs/sol.jpg", 100000);
          Planeta mercurio = new Planeta((float) sqrt(0.39f), "imgs/tierra.jpg", 1000, (int) sqrt(579), (int) (factorVelocidad / 47.87));
          Planeta venus = new Planeta((float) sqrt(0.95), "imgs/tierra.jpg", 1000, (int) sqrt(1082), (int) (factorVelocidad / 35.02));
          Planeta tierra = new Planeta(1f, "imgs/tierra.jpg", 3600, (int) sqrt(1496), (int) (factorVelocidad / 29.78) );
            Satelite luna = new Satelite((float) sqrt(3476f/12756f), "imgs/moon.jpg", 0, 3.7, 1000);
            tierra.addSatelite(luna);
          Planeta marte = new Planeta((float) sqrt(0.53), "imgs/tierra.jpg", 1000, (int) sqrt(2279), (int) (factorVelocidad / 24.08));
          Planeta jupiter = new Planeta((float) sqrt(11.2), "imgs/tierra.jpg", 1000, (int) sqrt(7785), (int) (factorVelocidad / 13.07));
            Satelite io = new Satelite((float) sqrt(3643f/12756f), "imgs/moon.jpg", 0, (int) sqrt(4), (int) (factorVelocidadS / 17.33));
            jupiter.addSatelite(io);
            Satelite europa = new Satelite((float) sqrt(3122f/12756f), "imgs/moon.jpg", 0, (int) sqrt(6.6), (int) (factorVelocidadS / 13.74));
            jupiter.addSatelite(europa);
            Satelite ganimedes = new Satelite((float) sqrt(5262f/12756f), "imgs/moon.jpg", 0, (int) sqrt(10.7), (int) (factorVelocidadS / 10.88));
            jupiter.addSatelite(ganimedes);
            Satelite calisto = new Satelite((float) sqrt(4821f/12756f), "imgs/moon.jpg", 0, (int) sqrt(18.8), (int) (factorVelocidadS / 8.2));
            jupiter.addSatelite(calisto);
          Planeta saturno = new Planeta((float) sqrt(9.41), "imgs/tierra.jpg", 1000, (int) sqrt(14330), (int) (factorVelocidad / 9.67));
          Planeta urano = new Planeta ((float) sqrt(3.98), "imgs/tierra.jpg", 1000, (int) sqrt(28770), (int) (factorVelocidad / 6.81));
          Planeta neptuno = new Planeta((float) sqrt(3.81), "imgs/tierra.jpg", 1000, (int) sqrt(44980), (int) (factorVelocidad / 5.48));
        sol.addPlaneta(mercurio);
        sol.addPlaneta(venus);
        sol.addPlaneta(tierra);
        sol.addPlaneta(marte);
        sol.addPlaneta(jupiter);
        sol.addPlaneta(saturno);
        sol.addPlaneta(urano);
        sol.addPlaneta(neptuno);
        
        todo.addChild(sol);
                
        crearLuces(this);
        this.addChild(todo);
        
        astros.add(sol);
        astros.add(tierra);
        astros.add(luna);
        
    //    crearProyeccionSombra(tierra, new Point3d(0,0,0));
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
    }/*
    public BranchGroup crearProyeccionSombra(AstroOpaco op, Point3d puntoSol){
        BranchGroup bg = new BranchGroup();
        Sphere sp = op.getSphere();
        Shape3D sh = sp.getShape();
        TriangleStripArray g = (TriangleStripArray) sh.getGeometry();
        Point3d coordenada; 
        ArrayList <Point3d> coordenadas = new ArrayList <> ();
        for (int i=0; i<g.getVertexCount(); i++ ){
            coordenada = new Point3d();
            g.getCoordinate(i, coordenada);
//            coordenada.z = 0;
            coordenadas.add(coordenada);
        }
        TriangleStripArray t;
        int [] cadena = { coordenadas.size() };
        for (int i=0; i<1000000; i++){
            t = new TriangleStripArray(coordenadas.size(), GeometryArray.COORDINATES, cadena );
        }
        return bg;
    }
    public boolean estaSobreAlgo(Point3d p, ArrayList <Astro> astros){
        double tolerancia =0.01;
        Point3d coordenada;
        TriangleStripArray t;
        AstroOpaco b;
        for (Astro a : astros){
            if ( a.getSphere() != null){
                t = (TriangleStripArray) a.getSphere().getShape().getGeometry();
                for (int i=0; i< t.getVertexCount(); i++ ){
                    coordenada = new Point3d();
                    t.getCoordinate(i, coordenada);
                    if (p.distance(coordenada) < tolerancia)
                        return true;
                }
            }
        }
        return false;
    }*/
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
