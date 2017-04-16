package com.dimamon.roguelike10.map.generator.floor.impl;

import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.floor.AbstractFloorGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.dimamon.roguelike10.config.GameConfig.*;

public class GridFloorGenerator extends AbstractFloorGenerator {

    /**
     * Firstly generate the floor map, and then put creatures!
     */
    @Override
    protected GameTile[][] generateFloor() {

        //move to config
        int cellCount = FLOOR_SIZE_X / CELL_SIZE + FLOOR_SIZE_Y / CELL_SIZE;
        List<Cell> cells = new ArrayList<>();

        //INIT CELLS
        for (int i = 0; i < cellCount ; i++) {
            cells.add(new Cell());
        }

        //SET coords in CELLS
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {

                //Left
                if((x % CELL_SIZE == 0) && (y % 5 != 0)){
                    Cell c = new Cell();
                    c.downLeft = new Coord(x,y);
                    cells.add(c);
                }


                floorMap[x][y] = GameTileFactory.getFloor();
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
    }
}
