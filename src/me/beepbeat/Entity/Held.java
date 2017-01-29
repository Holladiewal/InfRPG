package me.beepbeat.Entity;

import me.beepbeat.Items.IInventory;
import me.beepbeat.Items.IItem;
import me.beepbeat.Items.InventoryEventBus;

import static me.beepbeat.Entity.Stats.Stärke;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Held extends Entity implements IInventory, InventoryEventBus.IInventoryEventListener {
    private String name = "Held";
    private IItem[] inventory = new IItem[0];

    public Held() {
        setupStats();
    }

    @Override
    void setupStats() {
        super.setupStats();
        baseStats.put(Stärke, 10);
    }

    public int getStat(Stats stat){
        return baseStats.get(stat) + boni.getOrDefault(stat, 0);
    }

    //region Inventory
    @Override
    public int getInventorySize() {
        return inventory.length;
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
