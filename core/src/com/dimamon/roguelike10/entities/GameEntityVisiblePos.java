package com.dimamon.roguelike10.entities;

import com.dimamon.roguelike10.entities.creatures.params.Pos;

/**
 * Created by dimamon on 4/15/17.
 */

public class GameEntityVisiblePos extends GameEntityVisible {

    protected Pos pos;

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }
}
