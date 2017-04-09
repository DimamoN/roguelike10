package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dimamon on 4/9/17.
 */

public interface GameObject {

    void render(SpriteBatch batch);
    void update();
    void dispose();
}
