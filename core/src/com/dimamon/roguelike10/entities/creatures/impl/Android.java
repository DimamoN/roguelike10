package com.dimamon.roguelike10.entities.creatures.impl;

import com.badlogic.gdx.graphics.Texture;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.entities.creatures.Creature;

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
        if(findEnemy() != null){
            log.log("ENEMY FOUND! :"+ findEnemy().toString());
        }else{
            act(Direction.random());
        }
    }

}
