package com.dimamon.roguelike10.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;

/**
 * Wrapper for Creatures
 * It makes Creature controllable by player
 * Created by dimamon on 4/9/17.
 */
public class Player extends Creature implements LibGdxable {

    public Player(Creature creature) {
        super("player", creature.getTexture());
    }

    @Override
    public void update() {
        updateUserInput();
    }

    /**
     * Method for using in main update loop
     */
    public void updateUserInput(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            move(Direction.UP);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            move(Direction.DOWN);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            move(Direction.LEFT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            move(Direction.RIGHT);
        }
    }

    public int getFloor() {
        return pos.floor;
    }

}
