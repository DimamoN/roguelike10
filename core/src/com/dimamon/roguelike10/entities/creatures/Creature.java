package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.GameEntityVisiblePos;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.Moving;
import com.dimamon.roguelike10.entities.creatures.params.Attributes;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.map.GameMap;


/**
 * Main creature entity
 */
public abstract class Creature extends GameEntityVisiblePos implements LibGdxable, Moving, Comparable<Creature> {

    protected Attributes attributes;

    public Creature(Texture texture, String name, int str, int dex, int mind) {
        this.texture = texture;
        this.name = name;
        this.attributes = new Attributes(str,dex,mind);
        this.pos = new Pos();
    }

    //FOR PLAYER (THINK ABOUT IT)
    public Creature(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
        this.attributes = new Attributes();
        this.pos = new Pos();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, MapUtils.toReal(pos.x), MapUtils.toReal(pos.y));
    }
    @Override
    public void update() {

    }
    @Override
    public void dispose() {
        texture.dispose();
    }


    public void move(Direction direction) {
        switch (direction){
            case UP:{
                if(canMove(direction)) pos.y = ++pos.y;
                break;
            }
            case RIGHT:{
                if(canMove(direction)) pos.x = ++pos.x;
                break;
            }
            case DOWN: {
                if(canMove(direction)) pos.y = --pos.y;
                break;
            }
            case LEFT:{
                if(canMove(direction)) pos.x = --pos.x;
                break;
            }
            default: log.debug("no such direction");
        }
    }

    public boolean canMove(Direction direction) {

        switch (direction) {
            case UP: return GameMap.getFloor(pos.floor)
                    .canMoveTo(pos.x,pos.y+1);
            case RIGHT: return GameMap.getFloor(pos.floor)
                    .canMoveTo(pos.x+1,pos.y);
            case DOWN: return GameMap.getFloor(pos.floor)
                    .canMoveTo(pos.x,pos.y-1);
            case LEFT: return GameMap.getFloor(pos.floor)
                    .canMoveTo(pos.x-1,pos.y);
            default: {
                log.error("no such direction");
                return false;
            }
        }
    }

    public Creature setFloor(int floor){
        this.pos.floor = floor;
        return this;
    }

    public void setPos(int x, int y){
        this.pos.x = x;
        this.pos.y = y;
    }

    public Pos getPos() {
        return pos;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }

    /**
     * Compare so, Creatures list can be sorted, to render
     * @param other
     * @return
     */
    @Override
    public int compareTo(Creature other) {
        return other.pos.y - this.pos.y;
    }


}
