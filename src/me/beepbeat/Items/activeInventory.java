package me.beepbeat.Items;

import me.beepbeat.Entity.Entity;
import me.beepbeat.Main;
import me.beepbeat.util.InventoryEventBus;

/**
 * Created by zacharias on 02.02.17.
 */
public class activeInventory implements IInventory {
    IItem[] inventory = new IItem[getInventorySize()];
    Entity holder;

    activeInventory(Entity holder){
        this.holder = holder;
    }

    @Override
    public int getInventorySize() {
        return 6;
    }

    @Override
    public IItem getItemInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public Entity getHolder() {
        return holder;
    }

    @Override
    public boolean ready() {
        return true;
    }

    @Override
    public void setInventorySize(int size) {

    }

    @Override
    public boolean addItemToInventory(IItem item) {
        for (int i = 0; i < inventory.length; i++) {
            IItem itemInSlot = inventory[i];
            if (itemInSlot == null) {
                return addItemToInventory(item, i);
            }
        }
        return false;
    }

    @Override
    public boolean addItemToInventory(IItem item, int slot) {
        inventory[slot] = item;
        update();
        return inventory[slot] == item;
    }

    @Override
    public void clearInventory(boolean sure) {
        if (sure) inventory = new IItem[getInventorySize()];
    }

    @Override
    public void update() {
        Main.inventoryEventBus.fire();
    }
}
