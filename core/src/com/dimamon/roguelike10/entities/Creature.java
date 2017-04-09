package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.graphics.Texture;

import java.awt.TextArea;


public abstract class Creature implements GameObject {

    protected String name;
    protected Texture texture;

    protected int x, y;
    protected int hp;

    protected int strength,dexterity,intellect;

    public Creature(Texture texture, String name, int strength, int dexterity, int intellect) {
        this.texture = texture;
        this.name = name;
        this.hp = 10;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intellect = intellect;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", texture=" + texture +
                ", x=" + x +
                ", y=" + y +
                ", hp=" + hp +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", intellect=" + intellect +
                '}';
    }
}
