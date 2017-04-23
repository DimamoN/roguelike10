package com.dimamon.roguelike10.config;

import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.generator.Coord;

import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;

/**
 * Created by dimamon on 4/9/17.
 */

public class MapUtils {

    private static Log log = new Log("map-utils");

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

    public static GameTile[][] safeAdd(Coord c, GameTile tile, GameTile[][] floor){
        return safeAdd(c.x, c.y, tile, floor);
    }

    public static GameTile[][] safeAdd(int x, int y, GameTile tile, GameTile[][] floor){

        if(x >= GameConfig.FLOOR_SIZE_X || y >= GameConfig.FLOOR_SIZE_Y) {
            return floor;
        } else {
            floor[x][y] = tile;
            return floor;
        }
    }

    public static GameTile[][] setLineXwithTile(int X, GameTile tile, GameTile[][] floor){

        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                if(x == X){
                    floor[x][y] = tile;
                }

            }
        }

        return floor;
    }

    public static GameTile[][] setLineYwithTile(int Y, GameTile tile, GameTile[][] floor){

        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                if(y == Y){
                    floor[x][y] = tile;
                }

            }
        }

        return floor;
    }

    public static GameTile[][] generateRoom(Coord coord, int MaxSize, GameTile tile, GameTile[][] floor){

        Random rnd = new Random();
        int roomSizeX = rnd.nextInt(MaxSize) + 1;
        int roomSizeY = rnd.nextInt(MaxSize) + 1;

        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                if(x >= coord.x && x <= coord.x + roomSizeX &&
                   y >= coord.y && y <= coord.y + roomSizeY){
                    floor[x][y] = tile;
                }

            }
        }

        return floor;
    }

    public static boolean connectTiles(Coord from, Coord to, GameTile tile, GameTile[][] floor){

        int deltaX = to.x - from.x;
        int deltaY = to.y - from.x;

        //Line x ->
        if(deltaX > 0 && deltaY == 0){
            for (int x = from.x; x < to.x ; x++) {
                    floor[x][from.y] = tile;
            }
            log.debug("line x right");
            return true;
        }
        //Line y up
        else if(deltaY > 0 && deltaX == 0){
            for (int y = from.y; y < to.y ; y++) {
                floor[from.x][y] = tile;
            }
            log.debug("line y up");
            return true;
        }
        //Line x <-
        else if(deltaX > 0 && deltaY == 0){
            for (int x = to.x; x < from.x ; x++) {
                floor[x][from.y] = tile;
            }
            log.debug("line x left");
            return true;
        }
        //Line y down
        else if(deltaY < 0 && deltaX == 0){
            for (int y = to.y; y < from.y ; y++) {
                floor[from.x][y] = tile;
            }
            log.debug("line y down");
            return true;
        }
        //Stairs
        else if(deltaX > 0 && deltaY > 0){
            //todo: STAIRS
            log.error("stairs");
            return false;
        }

        log.error("cant connect");
        return false;
    }

}
