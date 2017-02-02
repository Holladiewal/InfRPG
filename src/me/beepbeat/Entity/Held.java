package me.beepbeat.Entity;

import me.beepbeat.Items.IInventory;
import me.beepbeat.Items.IItem;

import java.util.Objects;

import static me.beepbeat.Entity.Stat.Stärke;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Held extends Entity {
    private String name = "";

    @Override
    void setupStats() {
        super.setupStats();
        baseStats.put(Stärke, 0);
    }

    public int getStat(String stat){
        return baseStats.get(stat) + boni.getOrDefault(stat, 0);
    }

    public void nameHeld(String name){
        assert this.name.equals("");
        this.name = name;
    }
}
