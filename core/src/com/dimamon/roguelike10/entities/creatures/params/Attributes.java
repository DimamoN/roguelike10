package com.dimamon.roguelike10.entities.creatures.params;

/**
 * Attributes of creatures
 * Created by dimamon on 4/15/17.
 */
public class Attributes {

    //Main attributes
    protected int str,dex,mind;

    protected int hp;

    public Attributes(){}

    public Attributes(int str, int dex, int mind) {
        this.str = str;
        this.dex = dex;
        this.mind = mind;
    }


    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getMind() {
        return mind;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "str=" + str +
                ", dex=" + dex +
                ", mind=" + mind +
                ", hp=" + hp +
                '}';
    }
}
