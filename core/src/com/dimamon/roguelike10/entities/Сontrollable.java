package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Direction;

/**
 * Wrapper for Creatures
 * It makes Creature controllable by player
 * Created by dimamon on 4/9/17.
 */
public class Сontrollable implements GameObject {

    private com.dimamon.roguelike10.entities.creatures.Creature controllableCreature;

    public Сontrollable(com.dimamon.roguelike10.entities.creatures.Creature controllableCreature) {
        this.controllableCreature = controllableCreature;
    }

    public com.dimamon.roguelike10.entities.creatures.Creature getControllableCreature() {
        return controllableCreature;
    }

    public void setControllableCreature(com.dimamon.roguelike10.entities.creatures.Creature controllableCreature) {
        this.controllableCreature = controllableCreature;
    }

    /**
     * Method for using in main update loop
     */
    public void updateUserInput(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            controllableCreature.move(Direction.UP);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            controllableCreature.move(Direction.DOWN);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            controllableCreature.move(Direction.LEFT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            controllableCreature.move(Direction.RIGHT);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        controllableCreature.render(batch);
    }

    @Override
    public void update() {
        updateUserInput();
        controllableCreature.update();
    }

    @Override
    public void dispose() {
        controllableCreature.dispose();
    }
}
