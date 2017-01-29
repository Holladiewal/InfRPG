package me.beepbeat.Items;

import me.beepbeat.Entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beepbeat/holladiewal on 29.01.2017.
 */
public class InventoryEventBus {
    public interface IInventoryEventListener {
        void event(Entity e);
    }
    List<IInventoryEventListener> listeners = new ArrayList<>();
    public void addListener(IInventoryEventListener listener){listeners.add(listener);}
    public void fire(Entity e){
        for (IInventoryEventListener lis : listeners) {
            lis.event(e);
        }
    }
    public void removeListener(IInventoryEventListener lis){listeners.remove(lis);}

}


