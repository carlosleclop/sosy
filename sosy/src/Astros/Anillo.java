/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3d;

/**
 *
 * @author CARLOS
 */
public class Anillo extends BranchGroup {
    double radio;
    
    Anillo(double anchura, double radioGrande, double radioPeq){
        
        BranchGroup bg = new BranchGroup();
        Shape3D shape;
                
        double [] coordenadasArriba = new double[360*2*3];
        double [] coordenadasAbajo = new double[360*2*3];
        
        int [] cadenas = { 360*2 };
        TriangleStripArray arriba,abajo;
        
        for(int i=0; i<360;i++){
            coordenadasArriba[(i*6)+0] = radioGrande*toRadians(cos(i));
            coordenadasArriba[(i*6)+1] = anchura/2;
            coordenadasArriba[(i*6)+2] = radioGrande*toRadians(sin(i));
            coordenadasArriba[(i*6)+3] = radioPeq*toRadians(cos(i));
            coordenadasArriba[(i*6)+4] = anchura/2;
            coordenadasArriba[(i*6)+5] = radioPeq*toRadians(sin(i));

            coordenadasAbajo[(i*6)+0] = radioGrande*toRadians(sin(i));
            coordenadasAbajo[(i*6)+1] = - anchura/2;
            coordenadasAbajo[(i*6)+2] = radioGrande*toRadians(cos(i));
            coordenadasAbajo[(i*6)+3] = radioPeq*toRadians(sin(i));
            coordenadasAbajo[(i*6)+4] = - anchura/2;
            coordenadasAbajo[(i*6)+5] = radioPeq*toRadians(cos(i));
        }
        
        arriba = new TriangleStripArray(coordenadasArriba.length/3, GeometryArray.COORDINATES, cadenas);
        arriba.setCoordinates(0, coordenadasArriba);
        shape = new Shape3D(arriba);
        
        abajo = new TriangleStripArray(coordenadasAbajo.length/3, GeometryArray.COORDINATES, cadenas);
        abajo.setCoordinates(0, coordenadasAbajo);
        shape.insertGeometry(abajo, 0);
        
        bg.addChild(shape);
        
        this.addChild(bg);
        
    }
    public double getRadio(){
        return radio;
    }
    public void setRadio(double r){
        radio = r;
    }
    public void actPlay(){
        
    }
    public void actPause(){
        
    }
}
