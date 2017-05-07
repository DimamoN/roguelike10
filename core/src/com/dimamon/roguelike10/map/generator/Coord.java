package com.dimamon.roguelike10.map.generator;

import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.GameConfig;

public class Coord {

    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coord random(){
        int randomX = MathUtils.random(0, GameConfig.FLOOR_SIZE_X);
        int randomY = MathUtils.random(0, GameConfig.FLOOR_SIZE_Y);
        return new Coord(randomX, randomY);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
