package com.dimamon.roguelike10.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.Action;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.config.PosUtils;
import com.dimamon.roguelike10.entities.GameEntity;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.entities.items.ItemsFactory;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.game.Turn;
import com.dimamon.roguelike10.map.gameTile.GameTile;
import com.dimamon.roguelike10.map.gameTile.GameTileFactory;
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.CreatureGenerator;
import com.dimamon.roguelike10.map.generator.floor.FloorGenerator;
import com.dimamon.roguelike10.screens.LoseScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_X;
import static com.dimamon.roguelike10.config.GameConfig.FLOOR_SIZE_Y;

public class GameFloor extends GameEntity implements LibGdxable, Turn {

    /**
     * Reference to map (and app instance)
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
        List<Creature> creaturesToAdd = CreatureGenerator.generateCreatures(floorNum);
        creaturesToAdd.stream().forEach(c -> c.setMap(map));

        addOnFloorRndSpace(creaturesToAdd,floorNum);

        // If level >= 2 set stairsUp
        if(stepUp != null){
            setupStairsUp(stepUp);
        }

        // Put steps to next level
        if(floorNum < GameConfig.FLOOR_COUNT-1){
            setupStairsDown();
        } else {
            setupEndTerminal();
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
        List<Creature> lifeCreatures = new ArrayList<>();

        //Check life of creatures
        //And update all creatures on life creatures
        for(Creature creature: creatures){
            if(creature.checkLife()){
                lifeCreatures.add(creature);
            } else {
                //Dead in this turn
                if(creature instanceof Player){
                    log.log("You dead");
                    map.app.setScreen(new LoseScreen(map.app));

                } else {
                    log.log("Creature "+creature.getName()+" is dead");
                }

                putItem(creature.getPos(),ItemsFactory.getHeal());
            }
        }
        creatures = lifeCreatures;

        //Creatures make turn's
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

    public Queue<Item> getOnPosItems(Pos pos){
        if(PosUtils.isInGameField(pos)){
            return floorMap[pos.x][pos.y].getItems();
        }
        return null;
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
    public boolean canMove(Pos pos){
        return !floorMap[pos.x][pos.y].isBlocking();
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

    /**
     * TODO: NEAREST, NOT ANY
     *
     * @param creature
     * @return nearest enemy
     */
    public Creature nearestEnemy(Creature creature) {

        Pos left = creature.getLeftVisCorner();
        Pos right = creature.getRightVisCorner();

        //Remove to avoid bug - find self
        List<Creature> creaturesWithoutOne = new ArrayList<>();
        creaturesWithoutOne.addAll(creatures);
        creaturesWithoutOne.remove(creature);

        Optional<Creature> enemy = creaturesWithoutOne.stream().filter(
                c -> PosUtils.isIn(c.getPos(), left, right)).findAny();

        if(enemy.isPresent()){
            return enemy.get();
        }

        return null;
    }

    //-----------------STAIRS----------------------------------------------------
    public Coord getStepUp() {
        return stepUp;
    }

    public Coord getStepDown() {
        return stepDown;
    }

    private void setupStairsDown(){

        Pos pos;

        //For first floor
        if(floorNum == 0){
            pos = MapUtils.getRandomFloorPos(floorMap, floorNum);
            stepDown = new Coord(pos.x, pos.y);
        } else {
            //For 2, and other floors

            boolean isOkStairDistance;
            boolean isCollide;

            //Do while coords are bad (not collide with stepUp)
            //And distance between UP and DOWN less than GameConfig.STAIRS_DISTANCE
            do {
                isCollide = false;
                isOkStairDistance = false;

                pos = MapUtils.getRandomFloorPos(floorMap, floorNum);

                if(MapUtils.getDistance(stepUp, pos.getCoord()) >= GameConfig.STAIRS_DISTANCE){
                    isOkStairDistance = true;
                }

                if(pos.x == stepUp.x && pos.y == stepUp.y){
                    isCollide = true;
                }

                stepDown = new Coord(pos.x, pos.y);
            } while (!isOkStairDistance || isCollide);
        }

        floorMap[pos.x][pos.y] = GameTileFactory.getStepLow();
    }

    public void setupStairsUp(Coord stepUp) {
        this.stepUp = stepUp;
        floorMap[stepUp.x][stepUp.y] = GameTileFactory.getStepUp();
    }

    private void setupEndTerminal() {
        Pos pos = MapUtils.getRandomFloorPos(floorMap, floorNum);
        floorMap[pos.x][pos.y].put(ItemsFactory.getEndTerminal());
    }

    //-----------------ITEMS-----------------------------------------------------

    public void putItem(Pos pos, Item item){
        floorMap[pos.x][pos.y].put(item);
    }

    public Item pickItem(Pos pos){
        Item item = floorMap[pos.x][pos.y].pick();
        return item;
    }

    //--------------------TEST METHODS------------------------------------------------------
    public void initMap(){

        cleanCreatures();

        // Generate tiles
        floorMap = floorGenerator.getFloor(stepUp);

        // Generate creatures
        List<Creature> creaturesToAdd = CreatureGenerator.generateCreatures(floorNum);
        creaturesToAdd.stream().forEach(c -> c.setMap(map));

        addOnFloorRndSpace(creaturesToAdd,floorNum);

        // If level >= 2 set stairsUp
        if(stepUp != null){
            setupStairsUp(stepUp);
        }

        // Put steps to next level
        if(floorNum < GameConfig.FLOOR_COUNT-1){
            setupStairsDown();
        } else {
            setupEndTerminal();
        }
    }
    public void cleanCreatures(){
        creatures.clear();
    }

}
