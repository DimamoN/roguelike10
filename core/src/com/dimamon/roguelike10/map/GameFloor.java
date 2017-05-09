package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.Action;
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
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.CreatureGenerator;
import com.dimamon.roguelike10.map.generator.floor.FloorGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;
import static com.dimamon.roguelike10.config.GameConfig.HEIGHT;
import static com.dimamon.roguelike10.config.GameConfig.TEXTURE_SIZE;
import static com.dimamon.roguelike10.config.GameConfig.WIDTH;

public class GameFloor extends GameEntity implements LibGdxable, Turn {

    /**
     * Reference to map
     */
    private GameMap map;
    private int floorNum;

    //TODO: Use dynamic sorted structure
    private List<Creature> creatures;
    private GameTile[][] floorMap = new GameTile[FLOOR_SIZE_X][FLOOR_SIZE_Y];
    private FloorGenerator floorGenerator;

    /**
     * Coords of stairs for going to another levels
     */
    private Coord stepUp;
    private Coord stepDown;

    public GameFloor(GameMap map, FloorGenerator floorGenerator, int floorNum, Coord stepUp) {

        this.map = map;
        this.floorNum = floorNum;
        this.creatures = new ArrayList<>();
        this.floorGenerator = floorGenerator;
        this.log = new Log("Gamemap");

        initMap(stepUp);
    }

    private void initMap(Coord stepUp){

        this.stepUp = stepUp;

        // Generate tiles
        floorMap = floorGenerator.getFloor(stepUp);

        // Generate creatures
        List<Creature> creaturesToAdd = CreatureGenerator.generateCreatures(5,floorNum);
        creaturesToAdd.stream().forEach(c -> c.setMap(map));

        addOnFloorRndSpace(creaturesToAdd,floorNum);

        // If level >= 2 set stairsUp
        if(stepUp != null){
            setStepUp(stepUp);
        }

        // Put steps to next level
        if(floorNum <= GameConfig.FLOOR_COUNT){
            setStepDown(floorNum);
        } else {
            setEnd();
        }


    }


    @Override
    public void render(SpriteBatch batch) {

        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {
                floorMap[x][y].render(batch, x, y);
            }
        }
        creatures.stream().forEach(c -> c.render(batch));
    }
    @Override
    public void update() {
        for (int x = 0; x < FLOOR_SIZE_X; x++) {
            for (int y = 0; y < FLOOR_SIZE_Y; y++) {
                floorMap[x][y].update();
            }
        }
        creatures.stream().forEach(c -> c.update());

        //dirty
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
        //todo: make destroy system
        creatures = creatures.stream().filter
                (c -> c.checkLife()).collect(Collectors.toList());

        creatures.stream().forEach(c -> c.turn());
    }

    public void addOnFloorRndSpace(Creature creature, int floor){
        creature.setPos(MapUtils.getRandomFloorPos(this.floorMap, floor));
        addCreature(creature);
    }

    public void addOnFloorRndSpace(List<Creature> creatures, int floor){
        creatures.stream().forEach(c -> addOnFloorRndSpace(c,floor));
    }

    public Action actionTo(int x, int y){

        // Check
        if(x < 0 || y < 0 || x >= FLOOR_SIZE_X || y >= FLOOR_SIZE_Y) return Action.NONE;
        if(floorMap[x][y].isBlocking()) return Action.NONE;

        // Attack
        if(isOnPos(x,y)) return Action.ATTACK;

        //Move
        return Action.MOVE;
    }

    public boolean isOnPos(Pos pos){
        return isOnPos(pos.x,pos.y);
    }

    public boolean isOnPos(int x, int y){
        boolean result = creatures.stream().anyMatch
                (c -> c.getPos().x == x && c.getPos().y == y);
//        log.debug("Is on pos "+x+":"+y+"="+result);
        return result;
    }

    public List<Creature> getOnPos(Pos pos){

        //Collectors toList - from 24 API LEVEL
        List<Creature> creaturesOnPos = creatures.stream().filter(
                (c -> c.getPos().x == pos.x && c.getPos().y == pos.y))
                .collect(Collectors.toList());

        return creaturesOnPos;
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    //TODO: CAN REFACTOR TO "IS ON TILE"
    public boolean isOnStepLow(Pos pos) {
        if(floorMap[pos.x][pos.y].isStepLow()) {
            return true;
        }
        else return false;
    }
    public boolean isOnStepUp(Pos pos) {
        if(floorMap[pos.x][pos.y].isStepUp()) {
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

    //-----------------STAIRS----------------------------------------------------
    public Coord getStepUp() {
        return stepUp;
    }

    public Coord getStepDown() {
        return stepDown;
    }

    private void setStepDown(int floor){

        Pos pos;

        if(floorNum == 0){
            pos = MapUtils.getRandomFloorPos(this.floorMap, floor);
            stepDown = new Coord(pos.x, pos.y);
        } else {
            do {
                pos = MapUtils.getRandomFloorPos(this.floorMap, floor);
                stepDown = new Coord(pos.x, pos.y);
            } while (pos.x == stepUp.x && pos.y == stepUp.y);
        }

        floorMap[pos.x][pos.y] = GameTileFactory.getStepLow();
    }

    public void setStepUp(Coord stepUp) {
        this.stepUp = stepUp;
        floorMap[stepUp.x][stepUp.y] = GameTileFactory.getStepUp();
    }

    private void setEnd() {
    }

}
