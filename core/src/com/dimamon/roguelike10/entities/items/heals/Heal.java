package com.dimamon.roguelike10.entities.items.heals;

import com.badlogic.gdx.graphics.Texture;
import com.dimamon.roguelike10.entities.items.Item;

/**
 * Created by dimamon on 5/8/17.
 */

public class Heal extends Item {

    private int power;

    public Heal(String name, int power, Texture texture) {
        super(name, texture);
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
