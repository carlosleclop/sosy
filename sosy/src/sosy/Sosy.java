package sosy;

import GUI.ControlWindow;
import Model.TheUniverse;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

import Astros.*;
/**
 *
 * @author fvelasco
 */
public class Sosy {

/*  public static void main(String[] args) {
    // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    canvas.setSize(800, 600);
    
    // Se crea el Universo con dicho Canvas3D
    TheUniverse universe = new TheUniverse (canvas);
    // Se crea la GUI a partir del Canvas3D y del Universo
    ControlWindow controlWindow = new ControlWindow (canvas, universe);
    // Se muestra la ventana principal de la aplicación
    controlWindow.showWindow ();
  }*/
    public static void main(String[] args) {
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
        Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        // Se le da el tamaño deseado
        canvas.setSize(800, 600);
        
        Canvas3D canvas2 = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        
        canvas2.setSize(400,400);

        // Se crea el Universo con dicho Canvas3D
        Universo universe = new Universo (canvas, canvas2);
        
        // Se crea la GUI a partir del Canvas3D y del Universo
        ControlWindow controlWindow2 = new ControlWindow (canvas, canvas2, universe);
        // Se muestra la ventana principal de la aplicación
        controlWindow2.showWindow ();
    }
  
}
