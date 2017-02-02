package me.beepbeat;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import me.beepbeat.util.InventoryEventBus;
import me.beepbeat.util.Kampfregel;

import java.util.ArrayList;
import java.util.List;

public class Main extends SimpleApplication{
    public static InventoryEventBus inventoryEventBus = new InventoryEventBus();
    public static void main(String[] args){
        new Main().start();
    }

    private static void critRechner(){
        int i, k = 0;
        List<Integer> j = new ArrayList<>();
        for (int z = 0; z < 10;z++) {
            for (i = 0; i < 100; i++) {
                if (Kampfregel.crit(null)) {
                    k++;
                }
            }
            j.add(k);
            k = 0;
        }
        int mean = 0, mc = 0;
        for (Integer it : j) {
            mean += it;
            mc++;
            //System.out.println(it);
        }
        System.out.println(mean / mc);
    }

    @Override
    public void simpleInitApp() {
        Geometry box = new Geometry("box", new Box(2,2,2));
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        box.setMaterial(mat);
        rootNode.attachChild(box);
    }
}
