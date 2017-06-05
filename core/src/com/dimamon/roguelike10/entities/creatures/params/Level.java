package com.dimamon.roguelike10.entities.creatures.params;

import com.dimamon.roguelike10.sound.Sounds;

/**
 * To increase level, should get (100 * Level_num) xp
 *
 * LEVEL 2 = 100XP
 * LEVEL 3 = 300XP
 * LEVEL 4 = 600XP
 * LEVEL 5 = 1000XP
 */

public class Level {

    public int level;
    public int xp;

    public Level() {
        level = 1;
        xp = 0;
    }

    /**
     * Add XP to this creature
     * If level up -> return true
     * @param xp
     * @return
     */
    public boolean addXP(int xp){
        this.xp += xp;
        return isLevelUP();
    }

    private boolean isLevelUP(){

        // calculate xp needed to level up
        int xpToLevelUP = nextLevelXP();

        if(xp >= xpToLevelUP){
            level++;
            Sounds.levelUp();
            return true;
        } else return false;
    }

    public int nextLevelXP(){

        int curLevel = level;

        int xpToLevelUP = 0;
        while (curLevel > 0){
            xpToLevelUP += curLevel * 100;
            curLevel--;
        }

        return xpToLevelUP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
