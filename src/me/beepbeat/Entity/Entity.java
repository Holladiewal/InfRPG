package me.beepbeat.Entity;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Entity {
    int Leben = 0;
    int Angriffswert = 0;

    public void schadenErleiden(int schaden){
        if (Leben - schaden < 1) System.out.println("ich bin tot");
        else Leben -= schaden;
    }

}
