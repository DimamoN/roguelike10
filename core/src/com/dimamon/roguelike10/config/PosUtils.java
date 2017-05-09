package com.dimamon.roguelike10.config;

import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.entities.creatures.params.Pos;

/**
 * Created by dimamon on 5/7/17.
 */

public class PosUtils {

    private static Log log = new Log("pos-utils");

    public static Pos plusDir(Pos pos, Direction direction){

        Pos newPos = new Pos();
        newPos.x = pos.x;
        newPos.y = pos.y;

        if(pos == null){
            log.error("NO POS");
            newPos = new Pos();
        }

        switch (direction){
            case NONE: return pos;
            case UP: {
                newPos.y++;
                return newPos;
            }
            case DOWN: {
                newPos.y--;
                return newPos;
            }
            case RIGHT: {
                newPos.x++;
                return newPos;
            }
            case LEFT: {
                newPos.x--;
                return newPos;
            }
            default: return newPos;
        }

    }

    public static boolean isInGameField(Pos pos) {
        if(pos.x < GameConfig.FLOOR_SIZE_X && pos.y < GameConfig.FLOOR_SIZE_Y
                && pos.x >= 0 && pos.y >= 0){
            return true;
        } else return false;
    }
}
