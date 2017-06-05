package com.dimamon.roguelike10.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.Act;
import com.dimamon.roguelike10.common.Action;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.PosUtils;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.entities.items.end.EndTerminal;
import com.dimamon.roguelike10.entities.items.heals.Heal;
import com.dimamon.roguelike10.game.gameLog.StaticGameLog;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.screens.WinScreen;
import com.dimamon.roguelike10.sound.Sounds;

/**
 * Wrapper for Creatures
 * It makes Creature controllable by player
 * Created by dimamon on 4/9/17.
 */
public class Player extends Creature implements LibGdxable {

    /**
     * For auto movement time
     */
    long moveTime = 0l;

    App app;

    /**
     * What player can do
     */
    private PlayerAbilities playerAbilities;

    public Player(Creature creature, App app) {
        super("Player", creature);
        playerAbilities = new PlayerAbilities();
        this.app = app;
    }

    @Override
    public void update() {
        updateUserInput();
    }
    @Override
    public void dispose() {
        super.dispose();
    }
    @Override
    public void turn() {
        Sounds.step();
    }

    /**
     * Method for using in main update loop
     */
    public void updateUserInput(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            onStairs();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            pickItem();
        }

        //UPDATED MOVEMENT
        if((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && isMoveTime()){
            act(Direction.UP);
            initTurn();
        }
        if((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && isMoveTime()){
            act(Direction.DOWN);
            initTurn();
        }
        if((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && isMoveTime()){
            act(Direction.LEFT);
            initTurn();
        }
        if((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && isMoveTime()){
            act(Direction.RIGHT);
            initTurn();
        }

        //TEST PUT ITEM
//        if(Gdx.input.isKeyJustPressed(Input.Keys.O)){
//            map.getCurrentFloor().putItem(pos, ItemsFactory.getHeal());
//        }
    }

    private boolean isMoveTime(){
        if(TimeUtils.timeSinceMillis(moveTime) > GameConfig.AUTOMOVE_TIME){
            moveTime = TimeUtils.millis();
            return true;
        }
        return false;
    }

    private void initTurn(){
        map.turn();
    }

    private void onStairs(){
        String result = map.onStairs();
        addToLog(result);
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    @Override
    public Item drop() {
        return null;
    }

    public int getFloor() {
        return pos.floor;
    }

    public void pickItem(){
        Item item = map.getCurrentFloor().pickItem(pos);
        if(item != null){
            Sounds.pick();
            useItem(item);
        }
    }

    /**
     * Directly act, with logging to GameLog
     *
     * @param act
     */
    @Override
    public void act(Act act){
        stats.updTurnCount();

        if(act.getAction() == Action.MOVE){
            addToLog("You move " + act.getDirection() +
                    " to "+ PosUtils.plusDir(pos,act.getDirection()));
            move(act.getDirection());

        }
        else if(act.getAction() == Action.ATTACK){
            attack(act.getDirection());
        }
    }

    private void addToLog(String message){
        StaticGameLog.addMessage(message);
    }

    /**
     * Main usage of the items by player
     * @param item
     */
    private void useItem(Item item){
        if(item instanceof Heal){
            playerAbilities.heal(item);
        } else if (item instanceof EndTerminal){

            addToLog("You win!");
            app.setScreen(new WinScreen(app));
        }
        initTurn();
    }

    class PlayerAbilities{

        public void heal(Item item){
            Heal heal = (Heal) item;
            int hpAdded = attributes.addHp(heal.getPower());
            if(hpAdded == 0){
                addToLog("Already full HP");
            } else {
                addToLog("Healing " + hpAdded + " HP with: " + heal.getName());
            }
            log.log("Healing + " + hpAdded + " HP with: " + heal.getName());
        }
    }

}
