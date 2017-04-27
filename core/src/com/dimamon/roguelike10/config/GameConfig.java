package com.dimamon.roguelike10.config;

/**
 * Created by dimamon on 4/9/17.
 */

public class GameConfig {

    /**
     * WIDTH AND HEIGHT OF GAME SCREEN IN PIXELS
     */
    public static final int HEIGHT = 480;
    public static final int WIDTH = 800;

    public static final int TEXTURE_SIZE = 32;

    /**
     * FLOOR SIZE IN BLOCKS (15:25)
     */
    public static final int FLOOR_SIZE_X = WIDTH/TEXTURE_SIZE;
    public static final int FLOOR_SIZE_Y = HEIGHT/TEXTURE_SIZE;


    /**
     * DEEPS OF GAME MAP
     */
    public static final int FLOOR_COUNT = 5  ;

}
