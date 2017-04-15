package com.dimamon.roguelike10.entities.creatures.params;

/**
 * Position of creature
 * Created by dimamon on 4/15/17.
 */

public class Pos {

    public int x, y;
    public int floor;

    public Pos() {}

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
        this.floor = 0;
    }

    public Pos(int x, int y, int floor) {
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                ", floor=" + floor +
                '}';
    }
}
