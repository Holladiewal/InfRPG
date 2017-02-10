package me.beepbeat.Entity;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.math.Vector3f;

/**
 * Created by zacharias on 09.02.17.
 */
public class BCC extends BetterCharacterControl {

    public BCC(){
        super();
    }

    public BCC(float radius, float height, float mass) {
        super(radius, height, mass);
    }

    public Vector3f getLocation(){
        //return location;
        return location.clone();
    }
}
