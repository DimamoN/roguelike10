package com.dimamon.roguelike10.config;

import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.generator.Coord;

import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;

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

    public static GameTile[][] connectTiles(Coord from, Coord to, GameTile tile, GameTile[][] floor){
        //todo
        return floor;
    }

}
