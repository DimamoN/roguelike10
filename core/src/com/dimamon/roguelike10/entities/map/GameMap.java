package com.dimamon.roguelike10.entities.map;

import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

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
public class GameMap {

    private static final int FLOOR_COUNT = GameConfig.FLOOR_COUNT;

    private static List<GameFloor> gameMap;

    //Init all floors
    static {
        gameMap = new ArrayList<GameFloor>();
        for (int i = 0; i < FLOOR_COUNT; i++) {
            gameMap.add(new GameFloor());
        }
    }

    public static GameFloor getFloor(int n){
        return gameMap.get(n);
    }

    /**
     * Add an object to a selected floor
     */
    public static void addOnFloor(Creature creature, int floor, int x, int y){
        gameMap.get(floor).put(creature, x, y);
    }

    public static void dispose(){
        for(GameFloor f : gameMap){
            f.dispose();
        }
    }

}
