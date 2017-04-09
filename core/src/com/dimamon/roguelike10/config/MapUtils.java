package com.dimamon.roguelike10.config;

/**
 * Created by dimamon on 4/9/17.
 */

public class MapUtils {

    /**
     * Convert coordinate from GameFloor to real pixels
     * Use for rendering
     * @param mapCoord
     * @return
     */
    public static int toReal(int mapCoord){
        return mapCoord * GameConfig.TEXTURE_SIZE;
    }

    /**
     * Convert coordinate from real pixels to GameFloor
     * @param realCoord
     * @return
     */
    public static int toMap(int realCoord){
        return realCoord / GameConfig.TEXTURE_SIZE;
    }

}
