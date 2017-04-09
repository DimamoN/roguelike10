package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.dimamon.roguelike10.common.Direction;


public abstract class Creature implements GameObject, Moving {

    protected String name;
    protected Texture texture;

    protected int x, y;
    protected int hp;

    protected int strength,dexterity,intellect;

    protected Logger log;

    public Creature(Texture texture, String name, int strength, int dexterity, int intellect) {
        this.texture = texture;
        this.name = name;
        this.hp = 10;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intellect = intellect;
        this.log = new Logger(name);
    }

    public void move(Direction direction) {
        switch (direction){
            case UP:{
                if(canMove(direction)) y = ++y;
                break;
            }
            case RIGHT:{
                if(canMove(direction)) x = ++x;
                break;
            }
            case DOWN: {
                if(canMove(direction)) y = --y;
                break;
            }
            case LEFT:{
                if(canMove(direction)) x = --x;
                break;
            }
            default: log.debug("no such direction");
        }
    }

    /**
     * TODO: Make public static global map object, so i can ask it for canMove();
     * @param direction
     * @return
     */
    public boolean canMove(Direction direction) {

        //test
        return true;
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
