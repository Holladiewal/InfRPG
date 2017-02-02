package me.beepbeat.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zacharias on 02.02.17.
 */
public class InventoryEventBus{
    List<InventoryEventListener> listeners = new ArrayList<>();
    public interface InventoryEventListener{
         public void event();
    }

    public void addListener(InventoryEventListener lis){
        listeners.add(lis);;
    }

    public void removeListener(InventoryEventListener lis){
        listeners.remove(lis);
    }

    public void fire(){
        for (InventoryEventListener lis : listeners) {
            lis.event();
        }
    }
}
