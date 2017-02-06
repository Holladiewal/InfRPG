package me.beepbeat.Entity;

import static me.beepbeat.Entity.Stat.*;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Zauberer extends Held {
    @Override
    void setupStats() {
        super.setupStats();
        baseStats.put(Zauberkraft, 10);
    }


}
