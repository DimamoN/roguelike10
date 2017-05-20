package com.dimamon.roguelike10.entities.creatures.impl;

import com.badlogic.gdx.graphics.Texture;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.config.PosUtils;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.map.GameMap;

/**
 * Created by dimamon on 4/9/17.
 */

public class Android extends Creature {


    public Android(Texture texture, String name, int strength, int dexterity, int intellect) {
        super(texture, name, strength, dexterity, intellect);
    }

    @Override
    public void turn() {
        handleTurn();
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
