package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;

import java.util.Set;
import java.util.TreeSet;

import static com.dimamon.roguelike10.config.GameConfig.*;

/**
 * Created by dimamon on 4/9/17.
 */
public class GameFloor implements LibGdxable {

    private Set<Creature> creatures = new TreeSet<>();
    private static GameTile[][] floorMap = new GameTile[FLOOR_SIZE_X][FLOOR_SIZE_Y];


    public GameFloor() {
        initMap();
    }

    /**
     * Simply add floor texture
     */
    private void initMap(){
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y ; y++) {
                floorMap[x][y] =  GameTileFactory.getFloor();

                //test
                if(y == FLOOR_SIZE_Y-1) floorMap[x][y] =  GameTileFactory.getWall();
                if(y == 0) floorMap[x][y] =  GameTileFactory.getWallUp();
                if(x == 0) floorMap[x][y] =  GameTileFactory.getWallRight();
                if(x == FLOOR_SIZE_X-1) floorMap[x][y] =  GameTileFactory.getWallLeft();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {

        for (int x = 0; x < WIDTH / TEXTURE_SIZE; x++) {
            for (int y = 0; y < HEIGHT / TEXTURE_SIZE; y++) {
                floorMap[x][y].render(batch, x, y);
            }
        }
        creatures.stream().forEach(c -> c.render(batch));
    }
    @Override
    public void update() {
        for (int x = 0; x < WIDTH / TEXTURE_SIZE; x++) {
            for (int y = 0; y < HEIGHT / TEXTURE_SIZE; y++) {
                floorMap[x][y].update();
            }
        }
        creatures.stream().forEach(c -> c.update());
    }
    @Override
    public void dispose() {
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y ; y++) {
                floorMap[x][y].dispose();
            }
        }
        creatures.stream().forEach(c -> c.dispose());
    }

    /**
     * Check : can unit move to pos(x,y)
     * Edges of map handled
     */
    public boolean canMoveTo(int x, int y){
        if(x < 0 || y < 0 || x >= FLOOR_SIZE_X || y >= FLOOR_SIZE_Y) return false;
        if(floorMap[x][y].isBlocking()) return false;
        if(isOnPos(x,y)) return false;
        return true;
    }

    public boolean isOnPos(int x, int y){
        return creatures.stream().anyMatch(c -> c.getPos().floor == x && c.getPos().y == y);
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

}
