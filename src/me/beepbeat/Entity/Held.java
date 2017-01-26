package me.beepbeat.Entity;

import me.beepbeat.util.Kampfregel;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Held extends Entity {
    String name = "Held";
    int stärke = 0;

    public void angreifen(Entity g){
        Kampfregel.crit(this);
    }
}
