/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author fvelasco
 */
class TheLights extends BranchGroup {
  
  TheLights () {
    Color3f white;
    Vector3f direction;
    Light aLight;
    
    // Se crea la luz ambiente
    aLight = new AmbientLight (new Color3f (0.2f, 0.2f, 0.2f));
    aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
    aLight.setEnable(true);
    this.addChild(aLight);
    
    // Se crea la primera luz
    white = new Color3f (1.0f, 1.0f, 1.0f);
    direction = new Vector3f (-4.0f, -2.0f, -3.0f);
    aLight = new DirectionalLight (white, direction);
    aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
    aLight.setCapability(Light.ALLOW_STATE_WRITE);
    aLight.setEnable (true);
    this.addChild(aLight);
    
    // Se crea la segunda luz
    aLight = new DirectionalLight (new Color3f (0.7f, 0.7f, 0.7f), 
                    new Vector3f (3.0f, 2.0f, 0.0f));
    aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
    aLight.setCapability(Light.ALLOW_STATE_WRITE);
    aLight.setEnable (true);
    this.addChild(aLight);
  }
  
  /// Este método es llamado desde la fachada
  void setOnOff (int lightIndex, boolean onOff) {
    if (lightIndex > 0 && lightIndex < this.numChildren()) {
      ((Light) this.getChild (lightIndex)).setEnable(onOff);
    }   
  }
  
}
