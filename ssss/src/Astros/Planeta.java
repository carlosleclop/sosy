/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astros;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Node;

/**
 *
 * @author CARLOS
 */
public class Planeta extends AstroOpaco {
    
    ArrayList <Satelite> satelites;
    ArrayList <Anillo> anillos;
    private BranchGroup bg;

    Planeta(float radio, String textureString, int velocidadRotacion, double distanciaSol, int velocidadRotacionSol){
        
        //Para poder picar
        this.setPickable(true);
        this.setCapability(Node.ENABLE_PICK_REPORTING);
        act = false;
        satelites = new ArrayList <> ();
        anillos = new ArrayList <> ();
        this.radio = radio;
        bg = new BranchGroup();
        
        sp = new Sphere (radio, 
                    Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 
                    64, getAppearance(textureString) );
        
        bg.addChild( sp );

        crearRotacionPropia(velocidadRotacion);
        crearTranslacion(distanciaSol);
        crearRotacionSobreAlgo(velocidadRotacionSol);
        
        rotacionPropia.addChild(bg);
        desplazamiento.addChild(rotacionPropia);
        rotacionCentro.addChild(desplazamiento);
        
        this.addChild(rotacionCentro);
    }

    public void addSatelite(Satelite s){
        desplazamiento.addChild(s);
        satelites.add(s);
    }
    public boolean deleteSatelite(Satelite s){
        desplazamiento.removeChild(s);
        return satelites.remove(s);
    }
    public void addAnillo(Anillo a){
        desplazamiento.addChild(a);
        anillos.add(a);
    }
    public boolean deleteAnillo(Anillo a){
        desplazamiento.removeChild(a);
        return anillos.remove(a);
    }
    public ArrayList <Satelite> getSatelites(){
        return satelites;
    }
    /*
    public ArrayList <Anillo> getAnillos(){
        return anillos;
    }*/
    
}
