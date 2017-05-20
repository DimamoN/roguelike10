package com.dimamon.roguelike10.config;

import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.entities.creatures.params.Pos;

/**
 * Created by dimamon on 5/7/17.
 */

public class PosUtils {

    private static Log log = new Log("pos-utils");

    public static Pos safeCreate(int x, int y){

        int resultX;
        int resultY;

        //CHECK X
        if(x < GameConfig.FLOOR_SIZE_X && x > 0){
            resultX = x;
        } else if (x < 0){
            resultX = 0;
        } else resultX = GameConfig.FLOOR_SIZE_X;

        //CHECK Y
        if(y < GameConfig.FLOOR_SIZE_Y && y > 0){
            resultY = y;
        } else if (y < 0){
            resultY = 0;
        } else resultY = GameConfig.FLOOR_SIZE_Y;

        return new Pos(resultX,resultY);
    }

    /**
     * Is pos in borders of left and right
     *
     * @param pos
     * @param left
     * @param right
     * @return
     */
    public static boolean isIn(Pos pos, Pos left, Pos right){
        if(pos.x >= left.x && pos.y >= left.y &&
           pos.x <= right.x && pos.y <= right.y){
            return true;
        }
        return  false;
    }

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
