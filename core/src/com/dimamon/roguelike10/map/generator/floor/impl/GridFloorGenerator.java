package com.dimamon.roguelike10.map.generator.floor.impl;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.floor.AbstractFloorGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.*;

public class GridFloorGenerator extends AbstractFloorGenerator {

    private Log log = new Log("grid-generator");

    public static final int CELL_SIZE = 6;

    List<Coord> roomsStart = new ArrayList<>();

    GameTile rock = GameTileFactory.getRock();
    GameTile floor = GameTileFactory.getFloor();
    GameTile wall = GameTileFactory.getStepLow();

    /**
     * Firstly generate the floor map, and then put creatures!
     */
    @Override
    protected GameTile[][] generateFloor() {

        //Generate room starts
        generateRoomGridRoomWithChance(0);

        //Generate rooms
        roomsStart.stream().forEach(r -> generateRoomFromCoord(r, floorMap));

        //Generate corridors
        generateCorridors(roomsStart, floorMap);

        return floorMap;
    }

    public static Coord coord(int x, int y){
        return new Coord(x,y);
    }

    private void generateRoomGridRoomWithChance(int roomChance){

        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                //Set points
                if(x % CELL_SIZE == 0 && y % CELL_SIZE == 0 && MathUtils.random(1,10) >= roomChance){

                    floorMap[x][y] = floor;
                    roomsStart.add(new Coord(x, y));
                } else {
                    floorMap[x][y] = rock;
                }
            }
        }

    }

    private void generateRoomFromCoord(Coord coord, GameTile[][] floorMap){

        int roomSizeX = MathUtils.random(2, CELL_SIZE);
        int roomSizeY = MathUtils.random(2, CELL_SIZE);

        for (int x = coord.x; x < coord.x + roomSizeX; x++) {
            for (int y = coord.y; y < coord.y + roomSizeY; y++) {
                MapUtils.safeAdd(x,y,floor,floorMap);
            }
        }

    }

    private void generateCorridors(List<Coord> roomsStart, GameTile[][] floorMap){

        Coord cur = roomsStart.get(0);
        Coord prev = roomsStart.get(0);
        boolean status;

        for (int i = 0; i < roomsStart.size() ; i++) {

            log.debug("from:" + prev + " to " + cur);
            status = MapUtils.connectTiles(prev, cur, wall, floorMap);
            log.debug("status: " + status);

            prev = cur;
            cur = roomsStart.get(i);
        }
    }

}
