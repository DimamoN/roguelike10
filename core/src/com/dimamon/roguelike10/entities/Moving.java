package com.dimamon.roguelike10.entities;

import com.dimamon.roguelike10.common.Direction;


public interface Moving {

    void move(Direction direction);
    boolean canMove(Direction direction);

}
