package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.common.Act;
import com.dimamon.roguelike10.common.Action;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.config.PosUtils;
import com.dimamon.roguelike10.entities.GameEntityVisiblePos;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.Moving;
import com.dimamon.roguelike10.entities.creatures.params.Attack;
import com.dimamon.roguelike10.entities.creatures.params.Attributes;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.game.Statistics;
import com.dimamon.roguelike10.game.Turn;
import com.dimamon.roguelike10.game.gameLog.StaticGameLog;
import com.dimamon.roguelike10.map.GameFloor;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.sound.Sounds;

import java.util.List;
import java.util.stream.Collectors;

import static com.dimamon.roguelike10.config.GameConfig.CREATURES_VOLUME;
import static com.dimamon.roguelike10.config.GameConfig.MAX_TRAP_DAMAGE;
import static com.dimamon.roguelike10.config.GameConfig.MIN_TRAP_DAMAGE;
import static com.dimamon.roguelike10.config.GameConfig.PLAYER_VOLUME;


/**
 * Main creature entity
 */
public abstract class Creature extends GameEntityVisiblePos implements LibGdxable, Moving, Comparable<Creature>,Turn {

    /**
     * Only reference to a map, to init turn
     */
    protected GameMap map;
    protected Attributes attributes;
    protected Statistics stats;

    public Creature(Texture texture, String name, int str, int dex, int mind) {
        this.texture = texture;
        this.name = name;
        this.attributes = new Attributes(str,dex,mind);
        this.pos = new Pos();
        this.log = new Log(name);
        this.stats = new Statistics();
    }

    //FOR PLAYER (THINK ABOUT IT)
    public Creature(String name, Creature creature) {
        this.name = name;
        this.texture = creature.getTexture();
        this.attributes = creature.getAttributes();
        this.pos = new Pos();
        this.log = new Log(name);
        this.stats = new Statistics();
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
                action = GameMap.getFloor(pos.floor).actionTo(pos.x+1,pos.y);
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

    /**
     * Directly act, with logging to GameLog
     *
     * @param act
     */
    public void act(Act act){

//        log.debug("Act = " + act);
        stats.updTurnCount();

        if(act.getAction() == Action.MOVE){
            move(act.getDirection());
        }
        else if(act.getAction() == Action.ATTACK){
            attack(act.getDirection());
        }

    }

    /**
     * Move & handle trap
     * @param direction
     */
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

        // HANDLE TRAP
        if(map.getCurrentFloor().isOnTrap(pos)){

            int damage = MathUtils.random(MIN_TRAP_DAMAGE,MAX_TRAP_DAMAGE);
            boolean attack = attributes.attack(damage);

            if(attack){
                if(this instanceof Player){
                    StaticGameLog.addMessage("You fell into a trap, -"+damage+" hp");
                    Sounds.trap(PLAYER_VOLUME);
                } else Sounds.trap(CREATURES_VOLUME);

                map.getCurrentFloor().removeObject(pos);
            } else {
                if(this instanceof Player){
                    StaticGameLog.addMessage("You evaded the trap");
                    Sounds.dodge(PLAYER_VOLUME);
                } else Sounds.dodge(CREATURES_VOLUME);
            }
        }


    }

    public void attack(Direction direction){

        // Whom we want to attack
        List<Creature> creaturesToAttack = map.getCurrentFloor()
                .getOnPos(PosUtils.plusDir(pos, direction));

        if(this instanceof Player){
            Sounds.attack();

            //Player is attacking enemies
            for (Creature creature : creaturesToAttack){

                creature.attackThis(new Attack(this, getAttackPower()));

            }

        } else {
            //Enemy is attacking player
            List<Creature> isPlayerHere = creaturesToAttack.stream()
                    .filter(creature -> creature instanceof Player)
                    .collect(Collectors.toList());

            //If there player - attack, if not - don't attack
            if(!isPlayerHere.isEmpty()){
                Sounds.attackMob();
                creaturesToAttack.forEach(
                        c -> c.attackThis(new Attack(this, getAttackPower())));
            }
        }
    }

    public Creature findEnemy(){
        GameFloor currentFloor = GameMap.getFloor(pos.floor);
        return currentFloor.nearestEnemy(this);
    }

    //-----------------------ATTACK AND LIFE---------------------------------
    /**
     * Attack this creature
     */
    public void attackThis(Attack attack){

        boolean wasHit = attributes.attack(attack.power);
        if(wasHit){

            attack.from.stats.updHitCount();
            attack.from.attributes.hit();

            if(attack.from instanceof Player){
                StaticGameLog.addMessage(this.name + " was hit with " + attack.power + " damage");
            }
            log.log("Was hit with " + attack.power + " damage");
        } else {
            stats.updDodgeCount();
            log.log("Successfully dodge " + attack.power + " damage");
        }
    }

    public int getAttackPower(){
        return this.getAttributes().getAttackPower();
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

    public Pos getLeftVisCorner(){
        return PosUtils.safeCreate(pos.x - this.getAttributes().getVision(),
                pos.y - this.getAttributes().getVision());
    }

    public Pos getRightVisCorner(){
        return PosUtils.safeCreate(pos.x + this.getAttributes().getVision(),
                pos.y + this.getAttributes().getVision());
    }


    //----------------------DROP---------------------------------------------
    public abstract Item drop();


    //----------------------OTHER---------------------------------------------

    /**
     * Compare so, Creatures list can be sorted, to render
     * @param other
     * @return
     */
    @Override
    public int compareTo(Creature other) {
        return other.pos.y - this.pos.y;
    }

    @Override
    public String toString() {
        return this.name +
                " " + attributes;
    }

    public Statistics getStats() {
        return stats;
    }
}
