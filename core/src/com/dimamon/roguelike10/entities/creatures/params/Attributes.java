package com.dimamon.roguelike10.entities.creatures.params;

import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.sound.Sounds;

import static com.dimamon.roguelike10.config.GameConfig.PLAYER_VOLUME;

/**
 * Attributes of creatures
 * Created by dimamon on 4/15/17.
 */
public class Attributes {

    //Main attributes
    //strength, dexterity, perception
    protected int str,dex,perc;

    protected int maxHp;
    protected int hp;

    protected int vision;

    public Attributes(){
        this.hp = GameConfig.DEFAULT_HP;
        this.maxHp = hp;
    }

    public Attributes(int str, int dex, int perc) {
        this.str = str;
        this.dex = dex;
        this.perc = perc;
        this.hp = GameConfig.DEFAULT_HP + str;
        this.maxHp = hp;
        this.vision = GameConfig.DEFAULT_VISION_DISTANCE + perc /3;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getPerc() {
        return perc;
    }

    public int getHp() {
        return hp;
    }

    public int getVision() {
        return vision;
    }

    /**
     * Safe add hp
     * @param
     */
    public int addHp(int add){

        int hpWas = hp;

        hp += add;
        if(hp > maxHp){
            hp = maxHp;
        }

        return hp - hpWas;
    }

    //-------------------------ATTACKING------------------------------------

    /**
     * When attacking another creature - first get attack power
     * @return
     */
    public int getAttackPower(){

        int attack = GameConfig.DEFAULT_ATTACK;
        attack += MathUtils.random(str/2);

        return attack;
    }

    /**
     * @param power
     * @return true - attack
     *         false - dodge
     */
    public boolean attack(int power){

        if(canDodge()){
            Sounds.dodge(PLAYER_VOLUME);
            return false;
        } else {
            hp -= power;
            if(!isAlive()) Sounds.monsterDeath();
            return true;
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
                ", perc=" + perc +
                ", hp=" + hp +
                '}';
    }

    public int getMaxHp() {
        return maxHp;
    }
}
