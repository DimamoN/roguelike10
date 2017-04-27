package com.dimamon.roguelike10.map.tiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dimamon on 4/9/17.
 */

public class TileFactory {

    private static final String FLOOR_TEXTURE = "tiles/road2.png";

    private static final String WALL_TEXTURE = "tiles/wall2.png";
    private static final String WALL_UP_TEXTURE = "tiles/wallUp.png";
    private static final String WALL_RIGHT_TEXTURE = "tiles/wallRight.png";
    private static final String WALL_LEFT_TEXTURE = "tiles/wallLeft.png";
    private static final String ROCK_TEXTURE = "tiles/rock.png";

    private static final String STEP_LOW = "tiles/stepLow.png";

    private static final Tile floor = new Tile(new Texture(FLOOR_TEXTURE),"road1.png", false);
    private static final Tile wall = new Tile(new Texture(WALL_TEXTURE),"road1.png", true);
    private static final Tile wallUp = new Tile(new Texture(WALL_UP_TEXTURE),"road1.png", true);
    private static final Tile wallRight = new Tile(new Texture(WALL_RIGHT_TEXTURE),"road1.png", true);
    private static final Tile wallLeft = new Tile(new Texture(WALL_LEFT_TEXTURE),"road1.png", true);
    private static final Tile rock = new Tile(new Texture(ROCK_TEXTURE),"road1.png", true);
    private static final Tile stepLow = new Tile(new Texture(STEP_LOW),"stepLow.png", false);


    public static Tile getFloor(){
        return floor;
    }

    public static Tile getWall(){
        return wall;
    }

    public static Tile getWallUp(){
        return wallUp;
    }

    public static Tile getWallRight(){
       return wallRight;
    }

    public static Tile getWallLeft(){
        return wallLeft;
    }

    public static Tile getRock(){
        return rock;
    }

    public static Tile getStepLow() {
        return stepLow;
    }
}
