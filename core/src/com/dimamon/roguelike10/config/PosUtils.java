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

        if(pos == null){
            log.error("NO POS");
            pos = new Pos();
        }

        switch (direction){
            case NONE: return pos;
            case UP: {
                pos.y++;
                return pos;
            }
            case DOWN: {
                pos.y--;
                return pos;
            }
            case RIGHT: {
                pos.x++;
                return pos;
            }
            case LEFT: {
                pos.x--;
                return pos;
            }
            default: return pos;
        }

    }
}
