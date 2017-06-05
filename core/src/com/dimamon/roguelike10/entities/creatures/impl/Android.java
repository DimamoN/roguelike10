package com.dimamon.roguelike10.entities.creatures.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.config.PosUtils;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.entities.items.ItemsFactory;
import com.dimamon.roguelike10.map.GameMap;

/**
 * Created by dimamon on 4/9/17.
 */

public class Android extends Creature {

    private static final int DROP_CHANCE = 45;

    //DROP CHANCES (TODO: NORM DROP SYSTEM)
    private static final int DROP_HEAL = 25;
    private static final int DROP_HEAL_BIG = 75;


    public Android(Texture texture, String name, int strength, int dexterity, int intellect) {
        super(texture, name, strength, dexterity, intellect);
    }

    @Override
    public void turn() {
        handleTurn();
    }

    @Override
    public Item drop() {

        int result = MathUtils.random(100);
        if(result > DROP_CHANCE){

            int item = MathUtils.random(0,100);
            if(item >= DROP_HEAL_BIG){
                return ItemsFactory.getHealBig();
            }
            else{
                return ItemsFactory.getHeal();
            }

        }

        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public String toString() {
        return super.toString();
    }



    /**
     * Handle turn
     * If creature saw the enemy - go and attack
     * Else - move random
     */
    private void handleTurn(){

        Creature enemy = findEnemy();

        if(enemy != null){
            Direction dir = PosUtils.getDirection(pos, enemy.getPos());

            if(GameMap.getFloor(pos.floor).canMove(PosUtils.plusDir(pos, dir))){
                log.log("ENEMY FOUND! :"+ enemy.getName() + " go " + dir);
                act(dir);
            } else {
                Direction random = Direction.random();
                log.log("ENEMY FOUND! :"+ enemy.getName() + " but stuck, so go " + random);
                act(random);
            }
        }else{
            act(Direction.random());
        }
    }

}
