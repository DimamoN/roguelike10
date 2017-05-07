package com.dimamon.roguelike10.map.generator.floor;

import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.generator.Coord;

public interface FloorGenerator {

    /**
     * Return Generated floor map
     * @param stepUp - coord of stairs up in generated lvl
     * @return
     */
    GameTile[][] getFloor(Coord stepUp);

}
