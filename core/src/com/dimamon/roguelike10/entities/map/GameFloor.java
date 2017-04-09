package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.entities.GameObject;

import static com.dimamon.roguelike10.config.GameConfig.*;

/**
 * Created by dimamon on 4/9/17.
 */
public class GameFloor implements GameObject{

    private static final int SIZE_X = WIDTH/TEXTURE_SIZE;
    private static final int SIZE_Y = HEIGHT/TEXTURE_SIZE;

    private static Tile[][] gamemap = new Tile[SIZE_X][SIZE_Y];


    public GameFloor() {
        initMap();
    }

    private void initMap(){
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y ; y++) {
                gamemap[x][y] =  TileFactory.getFloor();
            }
        }
    }

    /**
     * Check : can unit move to pos(x,y)
     * Edges of map handled
     */
    public boolean canMoveTo(int x, int y){

        if(x < 0 || y < 0 || x >= SIZE_X || y >= SIZE_Y) return false;
        if(gamemap[x][y].isBlocking()) return false;

        return true;
    }

    @Override
    public void render(SpriteBatch batch) {

        for (int x = 0; x < WIDTH / TEXTURE_SIZE; x++) {
            for (int y = 0; y < HEIGHT / TEXTURE_SIZE; y++) {
                gamemap[x][y].render(batch, x*TEXTURE_SIZE, y*TEXTURE_SIZE);
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y ; y++) {
                gamemap[x][y].dispose();
            }
        }
    }
}
