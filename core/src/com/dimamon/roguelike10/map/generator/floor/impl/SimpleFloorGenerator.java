package com.dimamon.roguelike10.map.generator.floor.impl;

import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.floor.AbstractFloorGenerator;

import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;


public class SimpleFloorGenerator extends AbstractFloorGenerator {

    @Override
    protected GameTile[][] generateFloor() {
        //test stepLow
        int putX = new Random().nextInt(FLOOR_SIZE_X-1)+2;
        int putY = new Random().nextInt(FLOOR_SIZE_Y-1)+2;

        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y ; y++) {
                floorMap[x][y] = GameTileFactory.getFloor();

                //test
                if(y == FLOOR_SIZE_Y-1) floorMap[x][y] =  GameTileFactory.getWall();
                if(y == 0) floorMap[x][y] =  GameTileFactory.getWallUp();
                if(x == 0) floorMap[x][y] =  GameTileFactory.getWallRight();
                if(x == FLOOR_SIZE_X-1) floorMap[x][y] =  GameTileFactory.getWallLeft();

                //put StepLow
                if(x == putX && y == putY) floorMap[x][y] =  GameTileFactory.getStepLow();
            }
        }

        return floorMap;
    }

}
