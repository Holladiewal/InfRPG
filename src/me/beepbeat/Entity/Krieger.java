package me.beepbeat.Entity;

import static me.beepbeat.Entity.Stats.Ausdauer;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Krieger extends Held {

    @Override
    void setupStats() {
        super.setupStats();
        baseStats.put(Ausdauer, 5);
    }
}
