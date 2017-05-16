package com.dimamon.roguelike10.entities.creatures.params;

import com.dimamon.roguelike10.map.generator.Coord;

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

    public Coord getCoord(){
        return new Coord(x,y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pos pos = (Pos) o;

        if (x != pos.x) return false;
        if (y != pos.y) return false;
        return floor == pos.floor;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + floor;
        return result;
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
