package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.game.Turn;
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.floor.FloorGenerator;
import com.dimamon.roguelike10.map.generator.floor.impl.GridFloorGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_COUNT;

/**
 * This is MAIN FULL GAME MAP
 * It contains all game floors
 *
 *  FLOOR 0  <- START HERE
 *  FLOOR 1
 *  FLOOR 2
 *  FLOOR_COUNT
 *
 * Created by dimamon on 4/10/17.
 */
public class GameMap implements LibGdxable, Turn {

    private Log log = new Log("GameMap");

    private static List<GameFloor> floors;
    private GameFloor currentFloor;
    private FloorGenerator floorGenerator = new GridFloorGenerator();

    private Player player;

    public GameMap(Player player) {

        this.player = player;
        player.setMap(this);

        initFloors();
        setCurrentFloor(0);
        putPlayerToFloorRandomSpace(0);
    }


    public void render(SpriteBatch batch){
        currentFloor.render(batch);
    }
    public void update() {
        currentFloor.update();
    }
    public void dispose(){
        floors.stream().forEach(f -> f.dispose());
    }
    @Override
    public void turn() {
        GameFloor currentFloor = floors.get(currentFloor());
        currentFloor.turn();
    }

    public static GameFloor getFloor(int n){
        return floors.get(n);
    }
    public void onStairs() {

        if(getFloor(player.getFloor()).isOnStepLow(player.getPos())){
            int nextFloor = player.getFloor() + 1;
            if(nextFloor >= GameConfig.FLOOR_COUNT){
                log.error("No more floors");
                return;
            }
            log.log("Going down, to " + nextFloor);
            // Remove from current floor
            getFloor(player.getFloor()).removeCreature(player);
            // Put on next floor
            putPlayerToFloor(nextFloor);
            player.setFloor(nextFloor);
            setCurrentFloor(nextFloor);
        }
        else if(getFloor(player.getFloor()).isOnStepUp(player.getPos())){
            int nextFloor = player.getFloor() - 1;
            if(nextFloor < 0){
                log.error("No more floors");
                return;
            }
            log.log("Going up, to " + nextFloor);
            // Remove from current floor
            getFloor(player.getFloor()).removeCreature(player);
            // Put on next floor
            putPlayerToFloor(nextFloor);
            player.setFloor(nextFloor);
            setCurrentFloor(nextFloor);
        }
    }
    public void putPlayerToFloorRandomSpace(int floor){
        floors.get(floor).addOnFloorRndSpace(player,floor);
    }
    public void setCurrentFloor(int floor){
        currentFloor = floors.get(floor);
    }

    //---------------------------------------PRIVATE---------------------------------------------
    private int currentFloor(){
        return player.getFloor();
    }
    public GameFloor getCurrentFloor() {
        return currentFloor;
    }
    private void initFloors(){
        floors = new ArrayList<>();

        GameFloor cur;
        GameFloor prev = null;

        for (int i = 0; i < FLOOR_COUNT; i++) {

            if(i == 0){
                cur = new GameFloor(this, floorGenerator, i, null);
            } else {
                cur = new GameFloor(this, floorGenerator, i,  prev.getStepDown());
            }

            prev = cur;
            floors.add(cur);
        }
    }
    private void addOnFloor(Creature creature, int floor){
        floors.get(floor).addCreature(creature);
    }
    private void putPlayerToFloor(int floor){
        addOnFloor(player, floor);
    }
}
