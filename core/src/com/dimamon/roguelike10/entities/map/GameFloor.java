package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.GameObject;
import com.dimamon.roguelike10.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

import static com.dimamon.roguelike10.config.GameConfig.*;

/**
 * Created by dimamon on 4/9/17.
 */
public class GameFloor implements GameObject{

    private static final int SIZE_X = WIDTH/TEXTURE_SIZE;
    private static final int SIZE_Y = HEIGHT/TEXTURE_SIZE;

    private static List<Creature> creatures = new ArrayList<>();
    private static GameTile[][] floorMap = new GameTile[SIZE_X][SIZE_Y];


    public GameFloor() {
        initMap();
    }

    /**
     * Simply add floor texture
     */
    private void initMap(){
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y ; y++) {
                floorMap[x][y] =  GameTileFactory.getFloor();

                //test
                if(y == SIZE_Y-1) floorMap[x][y] =  GameTileFactory.getWall();
                if(y == 0) floorMap[x][y] =  GameTileFactory.getWallUp();
                if(x == 0) floorMap[x][y] =  GameTileFactory.getWallRight();
                if(x == SIZE_X-1) floorMap[x][y] =  GameTileFactory.getWallLeft();
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
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y ; y++) {
                floorMap[x][y].dispose();
            }
        }
    }

    /**
     * Check : can unit move to pos(x,y)
     * Edges of map handled
     */
    public boolean canMoveTo(int x, int y){
        if(x < 0 || y < 0 || x >= SIZE_X || y >= SIZE_Y) return false;
        if(floorMap[x][y].isBlocking()) return false;
        return true;
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    public static List<Creature> getCreatures() {
        return creatures;
    }

    public static void setCreatures(List<Creature> creatures) {
        GameFloor.creatures = creatures;
    }
}
