package com.dimamon.roguelike10.entities.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.GameEntityVisible;
import com.dimamon.roguelike10.entities.LibGdxable;

/**
 * Created by dimamon on 4/10/17.
 */

public class Item extends GameEntityVisible implements LibGdxable {

    public Item(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    public void render(SpriteBatch batch, int x, int y) {
        batch.draw(texture, MapUtils.toReal(x),MapUtils.toReal(y));
    }

    //TODO: REMOVE!
    @Override
    public void render(SpriteBatch batch) {

    }

    //TODO: REMOVE!
    @Override
    public void update() {

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
