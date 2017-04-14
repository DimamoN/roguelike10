package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.entities.creatures.Creature;

/**
 * Wrapper for Creatures
 * It makes Creature controllable by player
 * Created by dimamon on 4/9/17.
 */
public class Player extends Creature implements GameObject {

    private Creature creature;
    private int floor;

    public Player(Creature creature) {
        super("player", creature.getTexture());
        this.creature = creature;
    }

    public Creature creature() {
        return creature;
    }

    public void setControllableCreature(Creature controllableCreature) {
        this.creature = controllableCreature;
    }

    /**
     * Method for using in main update loop
     */
    public void updateUserInput(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            creature.move(Direction.UP);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            creature.move(Direction.DOWN);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            creature.move(Direction.LEFT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            creature.move(Direction.RIGHT);
        }
    }


    public int getFloor() {
        return floor;
    }

    @Override
    public void render(SpriteBatch batch) {
        creature.render(batch);
    }

    @Override
    public void update() {
        updateUserInput();
        creature.update();
    }

    @Override
    public void dispose() {
        creature.dispose();
    }
}
