package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.GameObject;

/**
 * Created by dimamon on 4/9/17.
 */

public class GameMap implements GameObject{

    private static final int SIZE_X = 40;
    private static final int SIZE_Y = 30;

    private static Tile[][] gamemap = new Tile[SIZE_X][SIZE_Y];


    @Override
    public void render(SpriteBatch batch) {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y ; y++) {
                gamemap[x][y].render(batch);
            }
        }
    }

    @Override
    public void update() {

    }
    
    @Override
    public void dispose() {

    }
}
