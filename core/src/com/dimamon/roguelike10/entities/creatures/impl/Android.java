package com.dimamon.roguelike10.entities.creatures.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Act;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.creatures.Creature;

import java.util.Random;

/**
 * Created by dimamon on 4/9/17.
 */

public class Android extends Creature {


    public Android(Texture texture, String name, int strength, int dexterity, int intellect) {
        super(texture, name, strength, dexterity, intellect);
    }

    @Override
    public void turn() {
        act(Direction.random());
    }

    @Override
    public void update() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
