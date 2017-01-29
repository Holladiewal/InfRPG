package me.beepbeat.Items;

import me.beepbeat.Entity.Entity;

/**
 * Created by zacharias on 29.01.17.
 */
public interface IInventory{

    int getInventorySize();
    IItem getItemInSlot(int slot);
    Entity getHolder();
    boolean ready();
    void setInventorySize(int size);
    boolean addItemToInventory(IItem item);
    boolean addItemToInventory(IItem item, int slot);
    void clearInventory(boolean sure);
    void update();
}
