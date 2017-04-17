package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.GameEntity;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.game.Turn;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.CreatureGenerator;
import com.dimamon.roguelike10.map.generator.floor.FloorGenerator;
import com.dimamon.roguelike10.map.generator.floor.impl.GridFloorGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.*;

public class GameFloor extends GameEntity implements LibGdxable, Turn {

    //TODO: Use dynamic sorted structure
    private List<Creature> creatures;
    private static GameTile[][] floorMap = new GameTile[FLOOR_SIZE_X][FLOOR_SIZE_Y];
    private FloorGenerator floorGenerator;

    public GameFloor() {
        creatures = new ArrayList<>();
        this.log = new Log("Gamemap");
        this.floorGenerator = new GridFloorGenerator();
        initMap();
    }
    private void initMap(){
        floorMap = floorGenerator.getFloor();

        //TODO SHOULD KNOW FLOOR
//        int floor = 0;
//        List<Creature> creaturesToAdd = CreatureGenerator.generateCreatures(5,floor);
//        addOnFloorRndSpace(creaturesToAdd,floor);
    }

    @Override
    public void render(SpriteBatch batch) {

        for (int x = 0; x < WIDTH / TEXTURE_SIZE; x++) {
            for (int y = 0; y < HEIGHT / TEXTURE_SIZE; y++) {
                floorMap[x][y].render(batch, x, y);
            }
        }
        creatures.stream().forEach(c -> c.render(batch));
    }
    @Override
    public void update() {
        for (int x = 0; x < WIDTH / TEXTURE_SIZE; x++) {
            for (int y = 0; y < HEIGHT / TEXTURE_SIZE; y++) {
                floorMap[x][y].update();
            }
        }
        creatures.stream().forEach(c -> c.update());

        //test!
        Collections.sort(creatures);
    }
    @Override
    public void dispose() {
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y ; y++) {
                floorMap[x][y].dispose();
            }
        }
        creatures.stream().forEach(c -> c.dispose());
    }
    @Override
    public void turn() {
        creatures.stream().forEach(c -> c.turn());
    }

    public void addOnFloorRndSpace(Creature creature, int floor){
        creature.setPos(
                new Random().nextInt(GameConfig.FLOOR_SIZE_X-1),
                new Random().nextInt(GameConfig.FLOOR_SIZE_Y-1));
        addCreature(creature);
    }

    public void addOnFloorRndSpace(List<Creature> creatures, int floor){
        creatures.stream().forEach(c -> addOnFloorRndSpace(c,floor));
    }

    public boolean canMoveTo(int x, int y){
        if(x < 0 || y < 0 || x >= FLOOR_SIZE_X || y >= FLOOR_SIZE_Y) return false;
        if(floorMap[x][y].isBlocking()) return false;
        if(isOnPos(x,y)) return false;
        return true;
    }

    public boolean isOnPos(int x, int y){
        boolean result = creatures.stream().anyMatch(c -> c.getPos().x == x && c.getPos().y == y);
//        log.debug("Is on pos "+x+":"+y+"="+result);
        return result;
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

}
