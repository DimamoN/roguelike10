package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.GameObject;

/**
 * Created by dimamon on 4/9/17.
 */
public class Tile implements GameObject{

    private Texture texture;
    private String name;
    private int x,y;

    public Tile(Texture texture, String name) {
        this.texture = texture;
        this.name = name;
    }

    public Tile(Texture texture, String name, int x, int y) {
        this.texture = texture;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y);
    }

    public void render(SpriteBatch batch, int x, int y) {
        batch.draw(texture,x,y);
    }

    //TODO: remove?
    @Override
    public void update() {

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
