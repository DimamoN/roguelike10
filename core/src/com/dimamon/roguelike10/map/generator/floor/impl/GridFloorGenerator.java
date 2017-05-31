package com.dimamon.roguelike10.map.generator.floor.impl;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.map.GameMap;
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

    /**
     * Firstly generate the floor map, and then put creatures!
     */
    @Override
    protected GameTile[][] generateFloor() {

        // Generate room starts
        generateRoomGridRoomWithChance(50);

        // Add room with stepUp coord
        roomsStart.add(stepUp);

        // Generate rooms
        roomsStart.stream().forEach(r -> generateRoomFromCoord(r, floorMap));

        // Generate corridors
        generateCorridors(roomsStart, floorMap);

        return floorMap;
    }

    private void generateRoomGridRoomWithChance(int roomChance){
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                //Set room starts
                if(x % CELL_SIZE == 0 && y % CELL_SIZE == 0 &&
                        MathUtils.random(1,100) <= roomChance){
                    floorMap[x][y] = GameTileFactory.getFloor();
                    roomsStart.add(new Coord(x, y));
                } else {
                    floorMap[x][y] =  GameTileFactory.getRock();
                }
            }
        }

    }

    private void generateRoomFromCoord(Coord coord, GameTile[][] floorMap){

        int roomSizeX = MathUtils.random(2, CELL_SIZE);
        int roomSizeY = MathUtils.random(2, CELL_SIZE);

        for (int x = coord.x; x < coord.x + roomSizeX; x++) {
            for (int y = coord.y; y < coord.y + roomSizeY; y++) {
                MapUtils.safeAdd(x,y,GameTileFactory.getFloor(),floorMap);
            }
        }

    }

    private void generateCorridors(List<Coord> roomsStart, GameTile[][] floorMap){

        List<Coord> roomsRemain = roomsStart;

        //Get random room
        Coord currentRoom = roomsRemain.get(MathUtils.random(0,roomsRemain.size()-1));
        roomsRemain.remove(currentRoom);

        do {
            //Get next room (random)
            Coord nextRoom = roomsRemain.get(MathUtils.random(0,roomsRemain.size()-1));

//            log.debug("current room: " + currentRoom + " next room: " + nextRoom);
            MapUtils.connectTilesWithFloor(currentRoom, nextRoom, floorMap);
            currentRoom = nextRoom;
            roomsRemain.remove(nextRoom);

        } while (!roomsRemain.isEmpty());

    }

}
