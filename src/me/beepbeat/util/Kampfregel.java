package me.beepbeat.util;

import me.beepbeat.Entity.Entity;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Kampfregel {
    private static Wuerfel w6 = new Wuerfel(6), w10 = new Wuerfel(10);

    //23-24% chance
    public static boolean crit(Entity e){
        //defense/dmg half
        //return w10.wuerfeln() % w6.wuerfeln() == 0;
        //crit
        return w6.wuerfeln() % w10.wuerfeln() == 0;
    }
}
