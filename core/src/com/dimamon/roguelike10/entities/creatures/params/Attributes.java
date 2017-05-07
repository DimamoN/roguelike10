package com.dimamon.roguelike10.entities.creatures.params;

import com.dimamon.roguelike10.config.GameConfig;

/**
 * Attributes of creatures
 * Created by dimamon on 4/15/17.
 */
public class Attributes {

    //Main attributes
    protected int str,dex,mind;

    protected int hp;

    public Attributes(){
        this.hp = GameConfig.DEFAULT_HP;
    }

    public Attributes(int str, int dex, int mind) {
        this.str = str;
        this.dex = dex;
        this.mind = mind;
        hp = GameConfig.DEFAULT_HP + str;
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

    public int getHp() {
        return hp;
    }

    public void attack(int power){
        hp -= power;
    }

    public boolean isAlive(){
        return (hp > 0) ?  true : false;
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
