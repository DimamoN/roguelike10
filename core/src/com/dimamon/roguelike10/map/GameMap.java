package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.game.Turn;

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

    private static List<GameFloor> floors;
    private Player player;

    //Init all floors
    {
        floors = new ArrayList<>();
        for (int i = 0; i < FLOOR_COUNT; i++) {
            floors.add(new GameFloor());
        }
    }

    public GameMap(Player player) {
        this.player = player;
    }

    public void render(SpriteBatch batch){
        floors.get(player.getFloor()).render(batch);
    }
    public void update() {
        floors.get(player.getFloor()).update();
    }
    public void dispose(){
        floors.stream().forEach(f -> f.dispose());
    }
    @Override
    public void turn() {
        floors.get(player.getFloor()).turn();
    }

    public static GameFloor getFloor(int n){
        return floors.get(n);
    }
    /**
     * Add a creature to a selected floor
     */
    public void addOnFloor(Creature creature, int floor){
        floors.get(floor).addCreature(creature);
    }

    public void addOnFloorRndSpace(Creature creature, int floor){
        creature.setPos(
                new Random().nextInt(GameConfig.FLOOR_SIZE_X-1),
                new Random().nextInt(GameConfig.FLOOR_SIZE_Y-1));
        floors.get(floor).addCreature(creature);
    }

    public void putPlayerToFloor(int floor){
        addOnFloor(player, floor);
    }


}
