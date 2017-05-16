package com.dimamon.roguelike10.config;

import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
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

    public static Pos getRandomFloorPos(GameTile[][] floor, int floorN){
        do{
            int x = MathUtils.random(GameConfig.FLOOR_SIZE_X - 1);
            int y = MathUtils.random(GameConfig.FLOOR_SIZE_Y - 1);

            if(!floor[x][y].isBlocking()){
                return new Pos(x,y,floorN);
            }

        } while (true);
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


    /**
     * WARNING! MAKE DUBLICATES!
     * @param from
     * @param to
     * @param tile
     * @param floor
     * @return
     */
    @Deprecated
    public static boolean connectTiles(Coord from, Coord to, GameTile tile, GameTile[][] floor){

        int currentX = from.x;
        int currentY = from.y;

        do{

            if(currentX >= GameConfig.FLOOR_SIZE_X || currentY >= GameConfig.FLOOR_SIZE_Y){
                log.error("Connection tiles error");
                return false;
            }

            //Go right
            if(to.x > currentX){
                floor[currentX][currentY] = tile;
                currentX++;
            }
            //Else go left
            else if (to.x < currentX){
                floor[currentX][currentY] = tile;
                currentX--;
            }
            //Else go up
            else if (to.y > currentY){
                floor[currentX][currentY] = tile;
                currentY++;
            }
            //Else go down
            else if (to.y < currentY){
                floor[currentX][currentY] = tile;
                currentY--;
            }
            //On the spot
            else {
                return true;
            }
        }while (true);
    }

    public static boolean connectTilesWithFloor(Coord from, Coord to, GameTile[][] floor){

        int currentX = from.x;
        int currentY = from.y;

        do{

            if(currentX >= GameConfig.FLOOR_SIZE_X || currentY >= GameConfig.FLOOR_SIZE_Y){
                log.error("Connection tiles error");
                return false;
            }

            //Go right
            if(to.x > currentX){
                floor[currentX][currentY] = GameTileFactory.getFloor();
                currentX++;
            }
            //Else go left
            else if (to.x < currentX){
                floor[currentX][currentY] = GameTileFactory.getFloor();
                currentX--;
            }
            //Else go up
            else if (to.y > currentY){
                floor[currentX][currentY] = GameTileFactory.getFloor();
                currentY++;
            }
            //Else go down
            else if (to.y < currentY){
                floor[currentX][currentY] = GameTileFactory.getFloor();
                currentY--;
            }
            //On the spot
            else {
                return true;
            }
        }while (true);
    }

    /**
     * Return the sum of x and y distance between coords
     *
     * @param from
     * @param to
     * @return
     */
    public static int getDistance(Coord from, Coord to){

        if(from == null || to == null){
            return 999;
        }

        int distanceX = Math.abs(to.x - from.x);
        int distanceY = Math.abs(to.y - from.y);

        return distanceX+distanceY;
    }

}
