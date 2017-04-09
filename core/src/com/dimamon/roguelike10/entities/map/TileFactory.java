package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dimamon on 4/9/17.
 */

public class TileFactory {

    private static final String FLOOR_TEXTURE = "tiles/road2.png";
    private static final String WALL_TEXTURE = "tiles/wall2.png";

    public static Tile getFloor(){
        return new Tile(new Texture(FLOOR_TEXTURE),"road1.png", false);
    }

    public static Tile getWall(){
        return new Tile(new Texture(WALL_TEXTURE),"wall.png", true);
    }
}
