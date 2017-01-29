package me.beepbeat.Entity;

import java.util.HashMap;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Entity {

    HashMap<String, Integer> baseStats = new HashMap<>();
    HashMap<String, Integer> boni = new HashMap<>();

    public void schadenErleiden(int schaden){
        if (getLeben() - schaden < 1) System.out.println("ich bin tot");
        else addSchaden(schaden);
    }

    void setupStats(){
        baseStats.put("Leben", 0);
        baseStats.put("Angriff", 0);
    }
    int getLeben(){
        return baseStats.get("Leben") + boni.getOrDefault("Leben", 0);
    }
    void addSchaden(int schaden){
        boni.put("Leben", boni.getOrDefault("Leben", 0) - schaden);
    }


}
