package com.dimamon.roguelike10.entities.creatures.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.items.Item;

/**
 * Created by dimamon on 4/9/17.
 */

public class Demon extends Creature {

    public Demon(Texture texture, String name, int strength, int dexterity, int intellect) {
        super(texture, name, strength, dexterity, intellect);
    }

    @Override
    public void update() {

    }

    @Override
    public Item drop() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
