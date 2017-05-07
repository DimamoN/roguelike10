package com.dimamon.roguelike10.map.generator.floor;

import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.generator.Coord;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;

public abstract class AbstractFloorGenerator implements FloorGenerator{

    protected GameTile[][] floorMap;
    /**
     * This is the coord of stepUp on this level
     * When player is going downstairs - he will arrive at this coord
     */
    protected Coord stepUp;

    public GameTile[][] getFloor(Coord stepUp){

        if(stepUp != null){
            this.stepUp = stepUp;
        } else {
            this.stepUp = Coord.random();
        }

        floorMap = new GameTile[FLOOR_SIZE_X][FLOOR_SIZE_Y];
        floorMap = generateFloor();
        return floorMap;
    }

    protected abstract GameTile[][] generateFloor();

}
