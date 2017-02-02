package me.beepbeat.Entity;

import java.util.HashMap;

import static me.beepbeat.Entity.Stat.Angriff;
import static me.beepbeat.Entity.Stat.Leben;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Entity {

    HashMap<Stat, Integer> baseStats = new HashMap<>();
    HashMap<Stat, Integer> boni = new HashMap<>();
    private int schadenErlitten = 0;

    public void schadenErleiden(int schaden){
        if (getLeben() - schaden < 1) System.out.println("ich bin tot");
        else addSchaden(schaden);
    }

    void setupStats(){
        baseStats.put(Leben, 0);
        baseStats.put(Angriff, 0);
    }
    int getLeben(){return baseStats.get(Leben) + boni.getOrDefault(Leben, 0) - schadenErlitten;}
    void addSchaden(int schaden){schadenErlitten += schaden;}


}
