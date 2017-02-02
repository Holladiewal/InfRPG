package me.beepbeat;

import me.beepbeat.util.InventoryEventBus;
import me.beepbeat.util.Kampfregel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static InventoryEventBus inventoryEventBus = new InventoryEventBus();
    public static void main(String[] args) {

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
}
