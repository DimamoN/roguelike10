package com.dimamon.roguelike10.entities;

import com.dimamon.roguelike10.common.Direction;


public interface Moving {

    public void move(Direction direction);
    public boolean canMove(Direction direction);

}
