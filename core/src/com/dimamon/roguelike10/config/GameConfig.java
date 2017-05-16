package com.dimamon.roguelike10.config;

/**
 * Created by dimamon on 4/9/17.
 */

public class GameConfig {

    /**
     * WIDTH AND HEIGHT OF GAME SCREEN IN PIXELS
     * 1) 800 x 480  (25:15 blocks)
     * 2) 1280 x 720 (40:22) + 0,5
     */
//    public static final int HEIGHT = 480;
//    public static final int WIDTH = 800;

    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;

    public static final int TEXTURE_SIZE = 32;

    /**
     * OFFSET FROM TOP, IN BLOCKS
     */
    public static final int INTERFACE_OFFSET = 1;
    /**
     * FLOOR SIZE IN BLOCKS
     */
    public static final int FLOOR_SIZE_X = WIDTH/TEXTURE_SIZE;
    public static final int FLOOR_SIZE_Y = HEIGHT/TEXTURE_SIZE - INTERFACE_OFFSET;


    //------------------GAME PLAY-------------------------------------------------

    public static final long AUTOMOVE_TIME = 150l;

    /**
     * DEEPS OF GAME MAP
     */
    public static final int FLOOR_COUNT = 5;

    public static final int DEFAULT_HP = 10;
    public static final int DEFAULT_ATTACK = 10;
    public static final int DEFAULT_HEAL = 10;
    /**
     * In percent
     */
    public static final int DEFAULT_DODGE_CHANCE = 10;

}
