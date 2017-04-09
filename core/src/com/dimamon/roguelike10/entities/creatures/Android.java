package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dimamon on 4/9/17.
 */

public class Android extends Creature{


    public Android(Texture texture, String name, int strength, int dexterity, int intellect) {
        super(texture, name, strength, dexterity, intellect);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y);
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
