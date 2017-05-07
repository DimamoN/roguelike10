package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.GameEntity;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.game.Turn;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.CreatureGenerator;
import com.dimamon.roguelike10.map.generator.floor.FloorGenerator;
import com.dimamon.roguelike10.map.generator.floor.impl.GridFloorGenerator;
import com.dimamon.roguelike10.map.generator.floor.impl.SimpleFloorGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.dimamon.roguelike10.config.GameConfig.*;

public class GameFloor extends GameEntity implements LibGdxable, Turn {

    //TODO: Use dynamic sorted structure
    private List<Creature> creatures;
    private GameTile[][] floorMap = new GameTile[FLOOR_SIZE_X][FLOOR_SIZE_Y];
    private FloorGenerator floorGenerator;

    public GameFloor(FloorGenerator floorGenerator) {
        creatures = new ArrayList<>();
        this.floorGenerator = floorGenerator;
        this.log = new Log("Gamemap");
        initMap();
    }

    private void initMap(){

        //1) Generate tiles
        floorMap = floorGenerator.getFloor();

        //2) generate creatures
        //TODO: GAMEFLOOR SHOULD KNOW FLOOR
        int floor = 0;
        List<Creature> creaturesToAdd = CreatureGenerator.generateCreatures(15,floor);
        addOnFloorRndSpace(creaturesToAdd,floor);

        //3) Put steps to next level
        setStepLow(floor);
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
        creature.setPos(MapUtils.getRandomFloorPos(this.floorMap, floor));
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

    private void setStepLow(int floor){
        Pos pos = MapUtils.getRandomFloorPos(this.floorMap, floor);
        floorMap[pos.x][pos.y] = GameTileFactory.getStepLow();
    }

    public boolean isOnStepLow(Pos pos) {
        if(floorMap[pos.x][pos.y].isStepLow()) {
            return true;
        }
        else return false;
    }

    public boolean removeCreature(Creature creature){

        List<Creature> newcreatures = new ArrayList<>();

        for(Creature c : creatures){
            if(c != creature){
                newcreatures.add(c);
            }
        }
        creatures = newcreatures;

        return creatures.size() > newcreatures.size();
    }
}
