package me.beepbeat.Entity;

import me.beepbeat.Items.IInventory;
import me.beepbeat.Items.IItem;
import me.beepbeat.Items.InventoryEventBus;

import static me.beepbeat.Entity.Stats.Stärke;

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
        baseStats.put("Stärke", 0);
    }

    public int getStat(Stats stat){
        return baseStats.get(stat) + boni.getOrDefault(stat, 0);
    }

    public void nameHeld(String name){
        assert this.name.equals("");
        this.name = name;
    }

    @Override
    public IItem getItemInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public Entity getHolder() {
        return this;
    }

    @Override
    public boolean ready() {
        return getInventorySize() > 0;
    }

    @Override
    public void setInventorySize(int size) {
        inventory = new IItem[size];
    }

    @Override
    public boolean addItemToInventory(IItem item) {
        for (int i = 0;i < getInventorySize();i++){
            if (getItemInSlot(i) == null){
                addItemToInventory(item, i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addItemToInventory(IItem item, int slot) {
        inventory[slot] = item;
        return true;
    }

    @Override
    public void clearInventory(boolean sure) {
        if (sure) {
            inventory = new IItem[getInventorySize()];
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void event(Entity e) {

    }
    //endregion
}
