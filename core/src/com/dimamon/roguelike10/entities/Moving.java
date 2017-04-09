package com.dimamon.roguelike10.entities;

import com.dimamon.roguelike10.common.Direction;

/**
 * Created by dimamon on 4/9/17.
 */

public interface Moving {

    public void move(Direction direction);
    public boolean canMove(Direction direction);

}
