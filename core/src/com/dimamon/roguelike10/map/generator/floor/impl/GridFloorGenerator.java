package com.dimamon.roguelike10.map.generator.floor.impl;

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

    public static final int CELL_SIZE = 6;

    public static final int CELL_COUNT_X = FLOOR_SIZE_X / CELL_SIZE;
    public static final int CELL_COUNT_Y = FLOOR_SIZE_Y / CELL_SIZE;

    Cell[][] cells = new Cell[CELL_COUNT_X][CELL_COUNT_Y];
    List<Coord> roomsStart = new ArrayList<>();

    GameTile rock = GameTileFactory.getRock();
    GameTile floor = GameTileFactory.getFloor();

    /**
     * Firstly generate the floor map, and then put creatures!
     */
    @Override
    protected GameTile[][] generateFloor() {

        //INIT CELLS
//        List<Cell> cells = new ArrayList<>();
//
//        int cellX = 0;
//        int cellY = 0;
//
//        for (int x = 0; x < FLOOR_SIZE_X; x++) {
//            for (int y = 0; y < FLOOR_SIZE_Y; y++) {
//
//                if(cellX == x) {
//                    cells.add(new Cell(x,y));
//                }
//
//            }
//        }

        //DIVIDE TO GRID
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                //DRAW LINES
//                if(x % CELL_SIZE == 0 ){
//                    MapUtils.setLineXwithTile(x, rock, floorMap);
//                }
//                else if (y % CELL_SIZE == 0){
//                    MapUtils.setLineYwithTile(y, rock, floorMap);
//                }

                //Set points
                if(x % CELL_SIZE == 0 && y % CELL_SIZE == 0){
                    floorMap[x][y] = floor;

                    //test for room generator
                    roomsStart.add(new Coord(x,y));
                }
                else {
                    floorMap[x][y] = rock;
                }
            }
        }

        //Generate rooms
        roomsStart.stream().forEach(r -> generateRoomFromCoord(r, floorMap));

        return floorMap;
    }

    public static Coord coord(int x, int y){
        return new Coord(x,y);
    }

    private void generateRoomFromCoord(Coord coord, GameTile[][] floorMap){

        Random rnd = new Random();
        int roomSizeX = rnd.nextInt(CELL_SIZE);
        int roomSizeY = rnd.nextInt(CELL_SIZE);

        for (int x = coord.x; x < coord.x + roomSizeX; x++) {
            for (int y = coord.y; y < coord.y + roomSizeY; y++) {

                MapUtils.safeAdd(x,y,floor,floorMap);
            }
        }

    }

    /**
     * Cell for GRID generator
     * Contain down left coordinate
     */
    class Cell{

        int x,y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isIn(int X, int Y){
            if(X >= x && X<= x + CELL_SIZE &&
               Y >= y && Y <= y + CELL_SIZE){
                return true;
            }
            else return false;
        }
    }

}
