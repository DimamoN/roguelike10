package com.dimamon.roguelike10.map.generator.floor;

import com.dimamon.roguelike10.map.gameTile.GameTile;

public interface FloorGenerator {

    GameTile[][] getFloor();

}
