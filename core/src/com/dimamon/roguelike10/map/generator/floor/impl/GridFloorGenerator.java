package com.dimamon.roguelike10.map.generator.floor.impl;

import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.floor.AbstractFloorGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.dimamon.roguelike10.config.GameConfig.*;

public class GridFloorGenerator extends AbstractFloorGenerator {

    public static final int CELL_SIZE = 6;

    public static final int CELL_COUNT_X = FLOOR_SIZE_X / CELL_SIZE;
    public static final int CELL_COUNT_Y = FLOOR_SIZE_Y / CELL_SIZE;

    Cell[][] cells = new Cell[CELL_COUNT_X][CELL_COUNT_Y];

    GameTile rock = GameTileFactory.getRock();
    GameTile floor = GameTileFactory.getFloor();

    /**
     * Firstly generate the floor map, and then put creatures!
     */
    @Override
    protected GameTile[][] generateFloor() {

        //INIT CELLS

        //DIVIDE TO GRID
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                if(x % CELL_SIZE == 0){
                    MapUtils.setLineXwithTile(x, rock, floorMap);
                }
                else if (y % CELL_SIZE == 0){
                    MapUtils.setLineYwithTile(y, rock, floorMap);
                }
                else {
                    floorMap[x][y] = floor;
                }

            }
        }

        return floorMap;
    }

    /**
     * Cell for GRID generator
     * Contain 4 coordinates
     */
    class Cell{
        Coord downLeft;
        Coord upLeft;
        Coord upRight;
        Coord downRight;

        public boolean isIn(int x, int y){
            if(x >= downLeft.getX() && x<= downRight.getX() &&
               y >= downLeft.getY() && y <= upLeft.getY()){
                return true;
            }
            else return false;
        }
    }
}
