package me.beepbeat.Entity;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Krieger extends Held {

    @Override
    void setupStats() {
        super.setupStats();
        baseStats.put(Stat.Ausdauer, 0);
    }
}
