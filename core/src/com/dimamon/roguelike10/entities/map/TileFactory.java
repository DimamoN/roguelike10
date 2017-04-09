package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dimamon on 4/9/17.
 */

public class TileFactory {

    public Tile getFloor(){
        return new Tile(new Texture("tiles/floor"),"floor.png");
    }
}
