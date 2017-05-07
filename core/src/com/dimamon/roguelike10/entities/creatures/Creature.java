package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Act;
import com.dimamon.roguelike10.common.Action;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.config.PosUtils;
import com.dimamon.roguelike10.entities.GameEntityVisiblePos;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.Moving;
import com.dimamon.roguelike10.entities.creatures.params.Attributes;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.game.Statictics;
import com.dimamon.roguelike10.game.Turn;
import com.dimamon.roguelike10.map.GameFloor;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.sound.Sounds;

import java.util.List;


/**
 * Main creature entity
 */
public abstract class Creature extends GameEntityVisiblePos implements LibGdxable, Moving, Comparable<Creature>,Turn {

    /**
     * Only reference to a map, to init turn
     */
    protected GameMap map;
    protected Attributes attributes;
    protected Statictics stats;

    public Creature(Texture texture, String name, int str, int dex, int mind) {
        this.texture = texture;
        this.name = name;
        this.attributes = new Attributes(str,dex,mind);
        this.pos = new Pos();
        this.log = new Log(name);
        this.stats = new Statictics();
    }

    //FOR PLAYER (THINK ABOUT IT)
    public Creature(String name, Creature creature) {
        this.name = name;
        this.texture = creature.getTexture();
        this.attributes = creature.getAttributes();
        this.pos = new Pos();
        this.log = new Log(name);
        this.stats = new Statictics();
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
    @Override
    public void turn() {
        log.debug("do turn");
    }

    //------------------------ACTIONS---------------------------------------
    public Act choseAct(Direction direction) {

        Action action;

        switch (direction) {
            case UP: {
                action = GameMap.getFloor(pos.floor).actionTo(pos.x,pos.y+1);
                break;
            }
            case RIGHT: {
                action= GameMap.getFloor(pos.floor).actionTo(pos.x+1,pos.y);
                break;
            }
            case DOWN: {
                action = GameMap.getFloor(pos.floor).actionTo(pos.x,pos.y-1);
                break;
            }
            case LEFT: {
                action = GameMap.getFloor(pos.floor).actionTo(pos.x-1,pos.y);
                break;
            }
            case NONE: {
                action = Action.NONE;
                break;
            }
            default: {
                log.error("No such direction");
                action = Action.NONE;
            }
        }

        return new Act(action, direction);
    }

    public void act(Direction direction){
        act(choseAct(direction));
    }

    public void act(Act act){

//        log.debug("Act = " + act);
        stats.updTurnCount();

        if(act.getAction() == Action.MOVE){
            move(act.getDirection());
        }

        if(act.getAction() == Action.ATTACK){
            attack(act.getDirection());
        }

    }

    public void move(Direction direction) {

        switch (direction){
            case UP:{
                pos.y = ++pos.y;
                break;
            }
            case RIGHT:{
                pos.x = ++pos.x;
                break;
            }
            case DOWN: {
                pos.y = --pos.y;
                break;
            }
            case LEFT:{
                pos.x = --pos.x;
                break;
            }
            case NONE:{
                break;
            }
            default: {
                log.debug("No such direction");
            }
        }
    }

    public void attack(Direction direction){

        log.log("Attacking : " + direction);

        if(this instanceof Player){
            Sounds.attack();

            List<Creature> creaturesToAttack = map.getCurrentFloor()
                    .getOnPos(PosUtils.plusDir(pos, direction));

            creaturesToAttack.stream().forEach
                    (c -> c.attackThis(GameConfig.DEFAULT_ATTACK));
        }


    }

    //-----------------------ATTACK AND LIFE---------------------------------
    /**
     * Attack this creature
     */
    public void attackThis(int power){
        attributes.attack(power);
    }

    public boolean checkLife(){
        return attributes.isAlive();
    }

    public Creature setFloor(int floor){
        this.pos.floor = floor;
        return this;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public Attributes getAttributes() {
        return attributes;
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
