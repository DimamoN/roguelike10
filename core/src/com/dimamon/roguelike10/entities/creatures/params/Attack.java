package com.dimamon.roguelike10.entities.creatures.params;

import com.dimamon.roguelike10.entities.creatures.Creature;

/**
 * Created by dimamon on 6/6/17.
 */

public class Attack {

    public Creature from;
    public int power;

    public Attack(Creature from, int power) {
        this.from = from;
        this.power = power;
    }
}
