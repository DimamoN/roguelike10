package com.dimamon.roguelike10.entities.creatures.params;

import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.sound.Sounds;

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
    
    //-------------------------ATTACKING------------------------------------

    public void attack(int power){

        if(canDodge()){
            Sounds.dodge();
        } else {
            hp -= power;
        }

        if(!isAlive()){
            Sounds.monsterDeath();
        }
    }

    private boolean canDodge(){
        return MathUtils.random(0,100) < GameConfig.DEFAULT_DODGE_CHANCE + dex;
    }

    public boolean isAlive(){
        return (hp > 0) ?  true : false;
    }

    @Override
    public String toString() {
        return "{" +
                "str=" + str +
                ", dex=" + dex +
                ", mind=" + mind +
                ", hp=" + hp +
                '}';
    }

}
