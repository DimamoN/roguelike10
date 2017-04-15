package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.graphics.Texture;


public class GameEntityVisible extends GameEntity {

    protected Texture texture;

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
