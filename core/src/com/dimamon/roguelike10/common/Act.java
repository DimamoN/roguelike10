package com.dimamon.roguelike10.common;

/**
 * Created by dimamon on 5/7/17.
 */

public class Act {

    private Action action;
    private Direction direction;

    public Act(Action action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Act{" +
                "action=" + action +
                ", direction=" + direction +
                '}';
    }
}
