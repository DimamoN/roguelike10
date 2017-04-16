package com.dimamon.roguelike10.map.generator.floor;

import com.dimamon.roguelike10.map.gameTile.GameTile;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;

public abstract class AbstractFloorGenerator implements FloorGenerator{

    protected GameTile[][] floorMap;

    public GameTile[][] getFloor(){
        floorMap = new GameTile[FLOOR_SIZE_X][FLOOR_SIZE_Y];
        floorMap = generateFloor();
        return floorMap;
    }

    protected abstract GameTile[][] generateFloor();

}
