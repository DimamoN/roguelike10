package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.GameObject;
import com.dimamon.roguelike10.entities.Moving;
import com.dimamon.roguelike10.entities.map.GameMap;


/**
 * Main creature entity
 */
public abstract class Creature implements GameObject, Moving {

    //test
    protected int floor;

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

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, MapUtils.toReal(x), MapUtils.toReal(y));
    }

    @Override
    public void dispose() {
        texture.dispose();
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

    public boolean canMove(Direction direction) {

        switch (direction) {
            case UP: return GameMap.getFloor(floor).canMoveTo(x,y+1);
            case RIGHT: return GameMap.getFloor(floor).canMoveTo(x+1,y);
            case DOWN: return GameMap.getFloor(floor).canMoveTo(x,y-1);
            case LEFT: return GameMap.getFloor(floor).canMoveTo(x-1,y);
            default: {
                log.error("no such direction");
                return false;
            }
        }
    }

    public Creature setFloor(int floor){
        this.floor = floor;
        return this;
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
