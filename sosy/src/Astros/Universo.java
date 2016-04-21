package Astros;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author carlos
 */
public class Universo {
  // Atributos de relación
//  private TheBackground background;
//  private TheLights lights;
//  private TheScene scene;
//  private TheAxes axes;
    private Escena scene;
    private Background background;
    
  // ******* Constructor
  
  public Universo (Canvas3D canvas, Canvas3D canvas2) {
      
      VirtualUniverse universe = new VirtualUniverse ( ) ;
      Locale locale = new Locale ( universe ) ;  
      
      Transform3D transformPlanta = new Transform3D ( ) ;
    transformPlanta.lookAt (new Point3d (0 ,200 ,0) , new Point3d (0 ,0 ,0) , new Vector3d (0 ,0 ,-1) ) ;
    
transformPlanta.invert() ;
TransformGroup tgPlanta = new TransformGroup ( transformPlanta ) ;
ViewPlatform vpPlanta = new ViewPlatform ( ) ;
tgPlanta.addChild ( vpPlanta ) ;



View viewPlanta = new View ( ) ;
viewPlanta.setPhysicalBody (new PhysicalBody ( ) ) ;
viewPlanta.setPhysicalEnvironment (new PhysicalEnvironment ( ) ) ;
viewPlanta.setProjectionPolicy(View.PARALLEL_PROJECTION) ;
 viewPlanta.setScreenScalePolicy(View.SCALE_EXPLICIT);
viewPlanta.setScreenScale(0.0032f) ;
viewPlanta.setFrontClipDistance ( 0.3f ) ;
viewPlanta.setBackClipDistance (100.0f) ;
      
      viewPlanta.addCanvas3D( canvas ) ;
    viewPlanta.attachViewPlatform ( vpPlanta ) ;
      
      Transform3D transformPersp = new Transform3D ( ) ;
        transformPersp.lookAt (new Point3d (50,50,1), new Point3d (0,0,0), new Vector3d (0,1,0));
    transformPersp.invert( ) ;
    TransformGroup tgPersp = new TransformGroup ( transformPersp ) ;
    ViewPlatform vpPersp = new ViewPlatform ( ) ;
    tgPersp.addChild ( vpPersp ) ;
      
    
    View viewPersp = new View ( ) ;
    viewPersp.setPhysicalBody (new PhysicalBody ( ) ) ;
    viewPersp.setPhysicalEnvironment (new PhysicalEnvironment ( ) ) ;
    viewPersp.setProjectionPolicy(View.PERSPECTIVE_PROJECTION) ;
    viewPersp.setFieldOfView (Math . toRadians (45) ) ;
    viewPersp.setFrontClipDistance ( 0.1 ) ;
    viewPersp.setBackClipDistance (35) ;
    
    viewPersp.addCanvas3D( canvas2 ) ;
    viewPersp.attachViewPlatform( vpPersp ) ;
      
      BranchGroup vistas = new BranchGroup ( ) ;
        vistas.addChild ( tgPlanta ) ;
    vistas.addChild ( tgPersp ) ;
      
      //vistas.compile();
      
      locale.addBranchGraph(vistas);
      
    // Todo cuelga de un nodo raiz
    BranchGroup root = new BranchGroup();
    
    background = new Background();
//    background.setApplicationBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
    background.setApplicationBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));    
    Appearance app = new Appearance();
    //Texture texture = new TextureLoader("imgs/back.jpg", null).getTexture();
    //app.setTexture( texture );
    ColoringAttributes unColorPlano = new ColoringAttributes( new Color3f(0,0,0) , ColoringAttributes.SHADE_FLAT) ;
    app.setColoringAttributes(unColorPlano) ;

    Primitive sphere = new Sphere (1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, app);

    BranchGroup bgGeometry = new BranchGroup();
    bgGeometry.addChild(sphere);
    background.setGeometry(bgGeometry);
    
    root.addChild(background);
    
    // Se crea y se añade el fondo
    //background = new TheBackground ();
    //root.addChild(background);

    // Se crean las luces y se añaden
    //lights = new TheLights ();
    //root.addChild(lights);
    
    // Se crean y se añaden unos ejes de coordenadas al universo
    //axes = new TheAxes (10.0f);
    //root.addChild(axes);
    
    
    // Se crea y se añade la escena al universo
    scene = new Escena (); 
    root.addChild(scene);
    
    
    // Se crea el universo. La parte de la vista
    //SimpleUniverse universe = createUniverse (canvas2);
    
    
    
    // Se optimiza la escena y se cuelga del universo
    root.compile();
    //universe.addBranchGraph(root);
    locale.addBranchGraph(root);
  }
  
  // ******* Private
  
  private SimpleUniverse createUniverse (Canvas3D canvas) {
    // Se crea manualmente un ViewingPlatform para poder personalizarlo y asignárselo al universo
    ViewingPlatform viewingPlatform = new ViewingPlatform();
    
    // La transformación de vista, dónde se está, a dónde se mira, Vup
    TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
    Transform3D viewTransform3D = new Transform3D();
    viewTransform3D.lookAt (new Point3d (50,50,1), new Point3d (0,0,0), new Vector3d (0,1,0));
    viewTransform3D.invert();
    
    viewTransformGroup.setTransform (viewTransform3D);
    
// NO ES viewingPlatform.setBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));

    // El comportamiento, para mover la camara con el raton
    OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
//    orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 100.0f));
    orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 10000.0f));
    orbit.setZoomFactor (2.0f);
    viewingPlatform.setViewPlatformBehavior(orbit);
    
    // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
    Viewer viewer = new Viewer (canvas);
    View view = viewer.getView();
    view.setFieldOfView(Math.toRadians(45));
//    view.setBackClipDistance(50.0);
    view.setBackClipDistance(5000.0);


    // Se construye y devuelve el Universo con los parametros definidos
    return new SimpleUniverse (viewingPlatform, viewer);
  }
  
  // ******* Public
  
  public void closeApp (int code) {
    System.exit (code);
  }
  
  // Esta clase es la fachada del modelo. 
  // Recibe todas las solicitudes de actuación y las redirige a los objetos que corresponden
  
  /*
  public void setAppearance (Appearances ap) {
    scene.setAppearance(ap);
  }
  
  public void setLightsOnOff (int lightIndex, boolean onOff) {
    lights.setOnOff(lightIndex, onOff);
  }
  
  public void setPrimitive (PrimitiveBranches pr) {
    scene.setPrimitive(pr);
  }
 
  
  public void showAxes (boolean onOff) {
    axes.showAxes(onOff);
  }
*/
    public void setRotationOnOff (boolean onOff) {
        scene.setRotationOnOff(onOff);
    }
}
