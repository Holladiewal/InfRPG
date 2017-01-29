package me.beepbeat.Entity;

import java.util.HashMap;

import static me.beepbeat.Entity.Stats.Angriff;
import static me.beepbeat.Entity.Stats.Leben;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Entity {

    HashMap<Stats, Integer> baseStats = new HashMap<>();
    HashMap<Stats, Integer> boni = new HashMap<>();

    public void schadenErleiden(int schaden){
        if (getLeben() - schaden < 1) System.out.println("ich bin tot");
        else addSchaden(schaden);
    }

    void setupStats(){
        baseStats.put(Leben, 10);
        baseStats.put(Angriff, 2);
    }
    int getLeben(){return baseStats.get(Leben) + boni.getOrDefault(Leben, 0);}
    void addSchaden(int schaden){boni.put(Leben, boni.getOrDefault(Leben, 0) - schaden);}


}
