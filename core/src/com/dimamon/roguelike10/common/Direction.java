package com.dimamon.roguelike10.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by dimamon on 4/9/17.
 */

public enum Direction {
    UP, LEFT, DOWN, RIGHT;


    private static final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction random()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
