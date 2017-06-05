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

    public Level level;

    // Main attributes
    // strength, dexterity, perception
    private int str,dex,perc;

    private int maxHp;

    private int hp;
    private int vision;

    public Attributes(int str, int dex, int perc) {

        this.level = new Level();
        this.str = str;
        this.dex = dex;
        this.perc = perc;
        setupAdditionalAttributes();
        this.hp = maxHp;
    }

    private void setupAdditionalAttributes(){
        this.maxHp = GameConfig.DEFAULT_HP + str*2;
        this.vision = GameConfig.DEFAULT_VISION_DISTANCE + perc /3;
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
        return MathUtils.random(0,100) < GameConfig.DEFAULT_DODGE_CHANCE + dex*2;
    }

    public boolean isAlive(){
        return (hp > 0);
    }


    //----------LEVEL & XP-------------------------------------------------

    public void hit(){
        addXp(GameConfig.HIT_XP);
    }

    public boolean addXp(int xp){
        boolean levelUP = level.addXP(xp);
        if(levelUP){
            levelUpAttributes();
        }
        return levelUP;
    }

    public void levelUpAttributes(){

        int riseCount = GameConfig.LEVEL_UP_ATTRIBUTES;

        while(riseCount > 0) {
            int random = MathUtils.random(1, 3);

            switch (random) {
                case 1: {
                    str++;
                    break;
                }
                case 2: {
                    dex++;
                    break;
                }
                case 3: {
                    perc++;
                    break;
                }
                default:
            }
            riseCount--;
        }

        setupAdditionalAttributes();
    }

    //----------------GETTERS-----------------------------------------------

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

    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public String toString() {
        return "{" +
                "lvl = " + level +
                "str=" + str +
                ", dex=" + dex +
                ", perc=" + perc +
                ", hp=" + hp +
                '}';
    }

}
