package me.beepbeat.util;

import java.util.Random;

/**
 * Created by zacharias.schmitt on 24.01.2017.
 */
public class Wuerfel {
    int seitenZahl = 0;
    Random rndm = new Random();
    Wuerfel(int seitenZahl){
        this.seitenZahl = seitenZahl;
    }
    public int wuerfeln(){
        return rndm.nextInt(seitenZahl);
    }
}
